package a.a.a;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

import java.util.ArrayList;

public class Nx {

    public String a;
    public int b;
    public String c;
    public boolean d;
    public ArrayList<AttributeBuilder> e;
    public ArrayList<Nx> f;
    public String g;

    public Nx(String str) {
        this(str, false);
    }

    public Nx(String str, boolean z) {
        this.d = z;
        this.a = str;
        this.b = 0;
        this.e = new ArrayList<>();
        this.f = new ArrayList<>();
    }

    public final String a() {
        return addIndent(0);
    }

    public final String addIndent(int indentSize) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < b + indentSize; i++) {
            str.append("\t");
        }
        return str.toString();
    }

    public void a(int position, String namespace, String attr, String value) {
        e.add(position, new AttributeBuilder(this, namespace, attr, value));
    }

    public void a(Nx xmlBuilder) {
        xmlBuilder.b(b + 1);
        f.add(xmlBuilder);
    }

    public void a(String str) {
        c = str;
    }

    public void a(String namespace, String attr, String value) {
        e.add(new AttributeBuilder(this, namespace, attr, value));
    }

    public void b(String value) {
        e.add(new AttributeBuilder(this, value));
    }

    public String toCode() {
        StringBuilder resultCode = new StringBuilder();
        resultCode.append(a());
        resultCode.append("<");
        resultCode.append(a);
        for (AttributeBuilder attr : e) {
            if (e.size() <= 1 || d) {
                resultCode.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            } else {
                resultCode.append("\r\n");
                resultCode.append(addIndent(1));
                g = "\r\n" + addIndent(1);
            }
            resultCode.append(attr.toCode());
        }
        if (f.size() <= 0) {
            if (c == null || c.length() <= 0) {
                resultCode.append(" />");
            } else {
                resultCode.append(">");
                resultCode.append(c);
                resultCode.append("</");
                resultCode.append(a);
                resultCode.append(">");
            }
        } else {
            resultCode.append(">");
            resultCode.append("\r\n");
            for (Nx xmlBuilder : f) {
                resultCode.append(xmlBuilder.toCode());
            }
            resultCode.append(a());
            resultCode.append("</");
            resultCode.append(a);
            resultCode.append(">");
        }
        resultCode.append("\r\n");
        return resultCode.toString();
    }

    public String c() {
        return a.replaceAll("\\w*\\..*\\.", "");
    }

    public void b(int indentSize) {
        b = indentSize;
        if (f != null) {
            for (Nx nx : f) {
                nx.b(indentSize + 1);
            }
        }
    }

    class AttributeBuilder {

        public final Nx xmlBuilder;
        private String namespace;
        private String attr;
        private final String value;

        public AttributeBuilder(Nx xmlBuilder, String namespace, String attr, String value) {
            this.xmlBuilder = xmlBuilder;
            this.namespace = namespace;
            this.attr = attr;
            this.value = value;
        }

        public AttributeBuilder(Nx xmlBuilder, String value) {
            this.xmlBuilder = xmlBuilder;
            this.value = value;
        }

        public String toCode() {
            if (namespace != null && namespace.length() > 0) {
                return namespace + ":" + attr + "=" + "\"" + value + "\"";
            } else if (attr == null || attr.length() <= 0) {
                return value.replaceAll("\n", Nx.this.g);
            } else {
                return attr + "=" + "\"" + value + "\"";
            }
        }
    }
}