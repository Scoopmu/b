package com.besome.sketch.design;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.besome.sketch.beans.ProjectFileBean;
import com.besome.sketch.common.SrcViewerActivity;
import com.besome.sketch.editor.manage.ManageCollectionActivity;
import com.besome.sketch.editor.manage.font.ManageFontActivity;
import com.besome.sketch.editor.manage.image.ManageImageActivity;
import com.besome.sketch.editor.manage.library.ManageLibraryActivity;
import com.besome.sketch.editor.manage.sound.ManageSoundActivity;
import com.besome.sketch.editor.manage.view.ManageViewActivity;
import com.besome.sketch.editor.view.ProjectFileSelector;
import com.besome.sketch.lib.base.BaseAppCompatActivity;
import com.besome.sketch.lib.ui.CustomViewPager;
import com.besome.sketch.tools.CompileLogActivity;
import com.besome.sketch.tools.ExportApkActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.sketchware.remod.Resources;

import java.io.File;
import java.util.HashMap;

import a.a.a.Ay;
import a.a.a.DB;
import a.a.a.Dp;
import a.a.a.Ep;
import a.a.a.GB;
import a.a.a.MA;
import a.a.a.Xf;
import a.a.a.aB;
import a.a.a.bB;
import a.a.a.bC;
import a.a.a.br;
import a.a.a.by;
import a.a.a.cC;
import a.a.a.gg;
import a.a.a.jC;
import a.a.a.jr;
import a.a.a.kC;
import a.a.a.lC;
import a.a.a.mB;
import a.a.a.nq;
import a.a.a.oB;
import a.a.a.rs;
import a.a.a.to;
import a.a.a.uo;
import a.a.a.wq;
import a.a.a.xB;
import a.a.a.xo;
import a.a.a.yB;
import a.a.a.yq;
import dev.aldi.sayuti.editor.manage.ManageCustomAttributeActivity;
import dev.aldi.sayuti.editor.manage.ManageLocalLibraryActivity;
import id.indosw.mod.DirectEditorActivity;
import mod.agus.jcoderz.editor.manage.background.ManageBackgroundActivity;
import mod.agus.jcoderz.editor.manage.permission.ManagePermissionActivity;
import mod.agus.jcoderz.editor.manage.resource.ManageResourceActivity;
import mod.alucard.tn.apksigner.ApkSigner;
import mod.hey.studios.activity.managers.assets.ManageAssetsActivity;
import mod.hey.studios.activity.managers.java.ManageJavaActivity;
import mod.hey.studios.activity.managers.nativelib.ManageNativelibsActivity;
import mod.hey.studios.build.BuildSettings;
import mod.hey.studios.build.BuildSettingsDialog;
import mod.hey.studios.project.DesignActRunnable;
import mod.hey.studios.project.custom_blocks.CustomBlocksDialog;
import mod.hey.studios.project.proguard.ManageProguardActivity;
import mod.hey.studios.project.proguard.ProguardHandler;
import mod.hey.studios.project.stringfog.ManageStringfogActivity;
import mod.hey.studios.project.stringfog.StringfogHandler;
import mod.hey.studios.util.Helper;
import mod.hilal.saif.activities.android_manifest.AndroidManifestInjection;
import mod.hosni.fraj.compilerlog.CompileErrorSaver;
import mod.jbk.bundle.BundleToolCompiler;
import mod.tyron.compiler.Compiler;
import mod.tyron.compiler.IncrementalCompiler;

public class DesignActivity extends BaseAppCompatActivity implements OnClickListener, uo {

    public ImageView A;
    public boolean B = false;
    public View C;
    public int D = -1;
    public int E;
    public boolean F = false;
    public AdView G;
    public View H;
    public to I;
    public DesignActivity.f J = null;
    public Toolbar k;
    /**
     * The sc_id of the current opened project, like 605
     */
    public String l;
    public CustomViewPager m;
    public CoordinatorLayout n;
    public DrawerLayout o;
    public LinearLayout p;
    public yq q;
    public DB r;
    public DB s;
    public DB t;
    /**
     * The Run-Button in bottom right corner
     */
    public Button u;
    public ProjectFileSelector v;
    public jr w = null;
    public rs x = null;
    public br y = null;
    public oB z;

    public DesignActivity() {
    }

    public final void A() {
        HashMap<String, Object> var1 = lC.b(this.l);
        if (var1 != null) {
            var1.put("sketchware_ver", GB.d(getApplicationContext()));
            lC.b(this.l, var1);
        }
    }

    public void a(boolean var1) {
        jC.a(this.l, var1);
        jC.b(this.l, var1);
        kC var2 = jC.d(this.l, var1);
        jC.c(this.l, var1);
        cC.c(this.l);
        bC.d(this.l);
        if (!var1) {
            var2.f();
            var2.g();
            var2.e();
        }
    }

    public void b(String var1) {
        d().a(var1);
    }

    public void b(boolean var1) {
        if (var1) {
            m.l();
        } else {
            m.k();
        }

    }

    /**
     * Shows a Snackbar indicating that a problem occurred while compiling. Clicking the action button won't open a new activity.
     *
     * @param errorId The ID of the error message. Can be 900, 901, 1001, 1002, 1003, or any other value (the others don't get a specific error text).
     */
    public final void c(String errorId) {
        Snackbar snackbar = Snackbar.a(this.n, nq.a(getApplicationContext(), errorId), -2 /* BaseTransientBottomBar.LENGTH_INDEFINITE */);
        snackbar.a(xB.b().a(getApplicationContext(), 2131625038), new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mB.a()) {
                    snackbar.c();
                }
            }
        });
        //REMOVED: Looks bad.
        //snackbar.h().setAlpha(0.8F);
        /* Set the text color to yellow */
        snackbar.f(Color.YELLOW);
        snackbar.n();
    }

    /**
     * Shows a Snackbar indicating that a problem occurred while compiling. The user can click on "SHOW" to get to {@link CompileLogActivity}.
     *
     * @param error The error, to be later displayed as text in {@link CompileLogActivity}
     */
    public final void d(String error) {
        new CompileErrorSaver(q.b).setErrorText(error);
        Snackbar snackbar = Snackbar.a(this.n, "Show compile log", -2 /* BaseTransientBottomBar.LENGTH_INDEFINITE */);
        snackbar.a(xB.b().a(getApplicationContext(), 2131625038), new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mB.a()) {
                    snackbar.c();
                    Intent intent = new Intent(getApplicationContext(), CompileLogActivity.class);
                    intent.putExtra("error", error);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }
            }
        });
        //REMOVED: Looks bad.
        //snackbar.h().setAlpha(0.8F);
        /* Set the text color to yellow */
        snackbar.f(Color.YELLOW);
        snackbar.n();
    }

    public void e(int var1) {
        if (var1 == 188) {
            (new DesignActivity.a(getApplicationContext())).execute();
        }

    }

    public void finish() {
        jC.a();
        cC.a();
        bC.a();
        setResult(0, this.getIntent());
        super.finish();
    }

    public final void l() {
        boolean var1 = jC.c(this.l).g();
        boolean var2 = jC.b(this.l).g();
        boolean var3 = jC.d(this.l).q();
        boolean var4 = jC.a(this.l).d();
        boolean var5 = jC.a(this.l).c();
        if (var1 || var2 || var3 || var4 || var5) {
            s();
        }

    }

    public final void m() {
        if (this.G != null) {
            try {
                Builder var1 = new Builder();
                AdRequest var3 = var1.build();
                G.loadAd(var3);
            } catch (Exception ignored) {
            }
        }
    }

    public void n() {
        q.b(jC.b(this.l), jC.a(this.l), jC.c(this.l));
    }

    /**
     * Opens the debug APK to install.
     */
    public final void o() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (VERSION.SDK_INT >= 24) {
            Uri apkUri = FileProvider.a(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", new File(this.q.H));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(new File(this.q.H)), "application/vnd.android.package-archive");
        }

        startActivity(intent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!j.h() && (requestCode == 208 || requestCode == 209 || requestCode == 217 || requestCode == 226 || requestCode == 228 || requestCode == 233 || requestCode == 240 || requestCode == 223 || requestCode == 224) && !j.h()) {
            xo.k();
        }

        if (requestCode == 188) {
            new a(getApplicationContext()).execute();
        } else {
            if (requestCode == 217) {
                return;
            }
            if (requestCode == 226) {
                if (resultCode == -1) {
                    br var4 = this.y;

                    if (v != null) {
                        v.a();
                    }
                }
            } else {
                if (requestCode == 228) {
                    return;
                }
                if (requestCode == 233) {
                    if (resultCode == -1) {
                        w.j();
                    }
                } else {
                    if (requestCode == 263) {
                        if (resultCode == -1) {
                            ProjectFileBean var8 = data.getParcelableExtra("project_file");
                            v.setXmlFileName(var8);
                        }
                    } else {
                        if (requestCode == 462) {
                            if (resultCode == -1 && data.getBooleanExtra("req_update_design_activity", false)) {
                                w.j();
                            }
                        } else {
                            if (requestCode == 208) {
                                if (resultCode == -1) {
                                    if (v != null) {
                                        v.a();
                                    }
                                    if (w != null) {
                                        w.n();
                                    }
                                }
                            } else {
                                if (requestCode == 209) {
                                    if (resultCode == -1) {
                                        if (w != null) {
                                            w.i();
                                        }
                                    }
                                } else {
                                    if (requestCode == 223) {
                                        if (resultCode == -1) {
                                            if (x != null) {
                                                x.f();
                                            }
                                        }
                                    } else {
                                        if (requestCode == 224 && resultCode == -1) {
                                            if (y != null) {
                                                y.d();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    public void onBackPressed() {
        if (o.f(8388613)) {
            o.a(8388613);
        } else if (w.g()) {
            w.a(false);
        } else {
            if (E > 0) {
                E--;
                m.setCurrentItem(E);
            } else if (t.c("P12I2")) {
                k();
                if (!j.h()) {
                    xo.a(I);
                    xo.a(getApplicationContext(), 242);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            new e(getApplicationContext()).execute();
                        }
                    }, 500L);
                }

            } else {
                q();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (!mB.a()) {
            if (v.getId() == Resources.id.btn_execute) {
                new DesignActivity.a(getApplicationContext()).execute();
            } else if (v.getId() == Resources.id.btn_compiler_opt) {
                new AlertDialog.Builder(this)
                        .setTitle("Action")
                        .setSingleChoiceItems(
                                new String[]{
                                        "Build Settings",
                                        "Show last compile error"
                                },
                                -1,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        if (which == 0) {
                                            new BuildSettingsDialog(DesignActivity.this, l).show();
                                        } else if (which == 1) {
                                            new CompileErrorSaver(l).showDialog(DesignActivity.this);
                                        }
                                    }
                                })
                        .show();
            }
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (F) {
                H.setVisibility(View.VISIBLE);
                p.setVisibility(View.VISIBLE);
            } else {
                H.setVisibility(View.GONE);
                p.setVisibility(View.GONE);
            }
        } else {
            H.setVisibility(View.GONE);
            p.setVisibility(View.GONE);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Resources.layout.design);
        if (!j()) {
            finish();
        }

        if (savedInstanceState == null) {
            l = getIntent().getStringExtra("sc_id");
        } else {
            l = savedInstanceState.getString("sc_id");
        }

        r = new DB(getApplicationContext(), "P1");
        s = new DB(getApplicationContext(), "P2");
        t = new DB(getApplicationContext(), "P12");
        z = new oB();
        k = findViewById(Resources.id.toolbar);
        a(k);
        findViewById(Resources.id.layout_main_logo).setVisibility(View.GONE);
        d().d(true);
        d().e(true);
        k.setNavigationOnClickListener(Helper.getBackPressedClickListener(this));
        k.setPopupTheme(Resources.style.ThemeOverlay_ToolbarMenu);
        getSupportFragmentManager().a(new Xf.c() {
            @Override
            public void onBackStackChanged() {
            }
        });
        p = findViewById(Resources.id.layout_ads);
        o = findViewById(Resources.id.drawer_layout);
        o.setDrawerLockMode(1);
        n = findViewById(Resources.id.layout_coordinator);
        u = findViewById(Resources.id.btn_execute);
        u.setText(xB.b().a(this, Resources.string.common_word_run));
        u.setOnClickListener(this);
        findViewById(Resources.id.btn_compiler_opt).setOnClickListener(this);
        A = findViewById(Resources.id.img_orientation);
        C = findViewById(Resources.id.layout_btn_group);
        v = findViewById(Resources.id.file_selector);
        v.setScId(l);
        v.setOnSelectedFileChangeListener(new by() {
            @Override
            public void a(int i, ProjectFileBean projectFileBean) {
                if (i == 0) {
                    if (w != null && projectFileBean != null) {
                        int orientation = projectFileBean.orientation;
                        if (orientation == 0) {
                            A.setImageResource(Resources.drawable.ic_screen_portrait_grey600_24dp);
                        } else if (orientation == 1) {
                            A.setImageResource(Resources.drawable.ic_screen_landscape_grey600_24dp);
                        } else {
                            A.setImageResource(Resources.drawable.ic_screen_rotation_grey600_24dp);
                        }
                        w.a(projectFileBean);
                    }
                } else if (i == 1) {
                    if (x != null) {
                        if (projectFileBean != null) {
                            x.a(projectFileBean);
                            x.f();
                        } else {
                            return;
                        }
                    }
                    if (y != null && projectFileBean != null) {
                        y.a(projectFileBean);
                        y.d();
                    }
                }
            }
        });
        m = findViewById(Resources.id.viewpager);
        m.setAdapter(new DesignActivity.g(getSupportFragmentManager(), this));
        m.setOffscreenPageLimit(3);
        m.a(new ViewPager.e() {

            @Override
            public void a(int i) {
            }

            @Override
            public void a(int i, float v, int i1) {
            }

            @Override
            public void b(int i) {
                if (E == 1) {
                    if (x != null) {
                        x.c();
                    }
                } else if (E == 2 && y != null) {
                    y.c();
                }
                if (i == 0) {
                    if (w != null) {
                        w.c(true);
                        A.setVisibility(View.VISIBLE);
                        v.setFileType(0);
                        v.a();
                    }
                } else if (i == 1) {
                    if (w != null) {
                        A.setVisibility(View.GONE);
                        w.c(false);
                        v.setFileType(1);
                        v.a();
                        rs rsVar2 = x;
                        if (rsVar2 != null) {
                            rsVar2.f();
                        }
                    }
                } else {
                    if (w != null) {
                        w.c(false);
                        A.setVisibility(View.GONE);
                        v.setFileType(1);
                        v.a();
                        br brVar2 = y;
                        if (brVar2 != null) {
                            brVar2.d();
                        }
                    }
                }
                E = i;
            }
        });
        I = new to() {
            @Override
            public void d(int i) {
                Log.d("later_ads", "on later ads load completed is called");
                if (i == 242) {
                    new e(getApplicationContext()).execute();
                } else if (i == 243) {
                    new c(getApplicationContext()).execute();
                }
            }
        };
        H = findViewById(Resources.id.view_ads_boader);
        m.getAdapter().b();
        ((TabLayout) findViewById(Resources.id.tab_layout)).setupWithViewPager(m);
        if (!j.h()) {
            xo.a((uo) this);
            xo.c(getApplicationContext());
            p();
        } else {
            H.setVisibility(View.GONE);
            p.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(Resources.menu.design_menu, menu);
        return true;
    }

    @Override
    public void onDestroy() {
        if (G != null) {
            try {
                G.destroy();
            } catch (Exception ignored) {
            }
        }

        xo.j();
        xo.i();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId != Resources.id.design_actionbar_titleopen_drawer) {
            if (itemId == Resources.id.design_option_menu_title_save_project) {
                k();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new d(getApplicationContext()).execute();
                    }
                }, 500L);
            }
        } else if (!o.f(8388613)) {
            o.h(8388613);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        if (G != null) {
            try {
                G.pause();
            } catch (Exception ignored) {
            }
        }

        super.onPause();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        k();
        HashMap<String, Object> map = lC.b(l);
        D = yB.b(map, "sketchware_ver");
        b(yB.c(map, "my_ws_name"));
        q = new yq(getApplicationContext(), wq.d(l), map);
        m();

        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    new b(getBaseContext(), savedInstanceState).execute();
                }
            }, 500L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!j()) {
            finish();
        }

        if (G != null) {
            try {
                G.resume();
            } catch (Exception ignored) {
            }
        }

        long c = GB.c();
        if (c < 100L && c > 0L) {
            r();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("sc_id", l);
        v.b(outState);
        super.onSaveInstanceState(outState);
        if (!j()) {
            finish();
        }

        if (J != null && !J.isCancelled()) {
            J.cancel(true);
        }

        if (!B) {
            J = new DesignActivity.f(getApplicationContext());
            J.execute();
        }
    }

    public final void p() {
        p.removeAllViews();
        G = new AdView(this);
        G.setAdListener(new AdListener() {
            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(int i) {
                p.setVisibility(View.GONE);
                F = false;
                if (w != null) {
                    w.b(false);
                }
                super.onAdFailedToLoad(i);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                F = true;
                super.onAdLoaded();
            }
        });
        G.setLayoutParams(new LayoutParams(-1, -2));
        p.addView(G);
        G.setAdSize(AdSize.BANNER);
        G.setAdUnitId("ca-app-pub-7978947291427601/3558354213");
    }

    /**
     * Show a dialog asking about saving the project before quitting.
     * TODO: Include "Cancel" option as neutral choice.
     */
    public final void q() {
        aB dialog = new aB(this);
        dialog.b(xB.b().a(getApplicationContext(), Resources.string.design_quit_title_exit_projet));
        dialog.a(Resources.drawable.exit_96);
        dialog.a(xB.b().a(getApplicationContext(), Resources.string.design_quit_message_confirm_save));
        dialog.b(xB.b().a(getApplicationContext(), Resources.string.design_quit_button_save_and_exit), new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mB.a()) {
                    dialog.dismiss();
                    try {
                        k();
                        if (!j.h()) {
                            xo.a(I);
                            xo.a(getApplicationContext(), 242);
                            return;
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                new e(getApplicationContext()).execute();
                            }
                        }, 500);
                    } catch (Exception e) {
                        e.printStackTrace();
                        h();
                    }
                }
            }
        });
        dialog.a(xB.b().a(getApplicationContext(), Resources.string.common_word_exit), new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mB.a()) {
                    dialog.dismiss();
                    try {
                        k();
                        if (!j.h()) {
                            xo.a(I);
                            xo.a(getApplicationContext(), 243);
                            return;
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                new c(getApplicationContext()).execute();
                            }
                        }, 500);
                    } catch (Exception e) {
                        e.printStackTrace();
                        h();
                    }
                }
            }
        });
        dialog.show();
    }

    /**
     * Show a dialog warning the user about low free space.
     */
    public final void r() {
        aB dialog = new aB(this);
        dialog.b(xB.b().a(getApplicationContext(), Resources.string.common_word_warning));
        dialog.a(Resources.drawable.break_warning_96_red);
        dialog.a(xB.b().a(getApplicationContext(), Resources.string.common_message_insufficient_storage_space));
        dialog.b(xB.b().a(getApplicationContext(), Resources.string.common_word_ok), new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public final void s() {
        B = true;
        aB dialog = new aB(this);
        dialog.a(Resources.drawable.data_backup_96);
        dialog.b(xB.b().a(getApplicationContext(), Resources.string.design_restore_data_title));
        dialog.a(xB.b().a(getApplicationContext(), Resources.string.design_restore_data_message_confirm));
        dialog.b(xB.b().a(getApplicationContext(), Resources.string.common_word_restore), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mB.a()) {
                    boolean g = jC.c(l).g();
                    boolean g2 = jC.b(l).g();
                    boolean q = jC.d(l).q();
                    boolean d = jC.a(l).d();
                    boolean c = jC.a(l).c();
                    if (g) {
                        jC.c(l).h();
                    }
                    if (g2) {
                        jC.b(l).h();
                    }
                    if (q) {
                        jC.d(l).r();
                    }
                    if (d) {
                        jC.a(l).h();
                    }
                    if (c) {
                        jC.a(l).f();
                    }
                    if (g) {
                        jC.b(l).a(jC.c(l));
                        jC.a(l).a(jC.c(l).d());
                    }
                    if (g2 || g) {
                        jC.a(l).a(jC.b(l));
                    }
                    if (q) {
                        jC.a(l).b(jC.d(l));
                        jC.a(l).c(jC.d(l));
                        jC.a(l).a(jC.d(l));
                    }
                    DesignActivity.this.v.a();
                    B = false;
                    dialog.dismiss();
                }
            }
        });
        dialog.a(xB.b().a(getApplicationContext(), Resources.string.common_word_no), new OnClickListener() {
            @Override
            public void onClick(View v) {
                B = false;
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * Opens {@link ManageCollectionActivity}.
     */
    public void t() {
        Intent intent = new Intent(getApplicationContext(), ManageCollectionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        startActivityForResult(intent, 233);
    }

    /**
     * Opens {@link AndroidManifestInjection}.
     */
    public void toAndroidManifest() {
        Intent intent = new Intent(getApplicationContext(), AndroidManifestInjection.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        intent.putExtra("file_name", v.g);
        startActivity(intent);
    }

    /**
     * Opens {@link ManageCustomAttributeActivity}.
     */
    public void toAppCompatInjection() {
        Intent intent = new Intent(getApplicationContext(), ManageCustomAttributeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        intent.putExtra("file_name", v.f);
        startActivity(intent);
    }

    /**
     * Opens {@link ManageAssetsActivity}.
     */
    public void toAssets() {
        Intent intent = new Intent(getApplicationContext(), ManageAssetsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        startActivity(intent);
    }

    /**
     * Opens {@link ManageBackgroundActivity}.
     */
    public void toBroadcast() {
        Intent intent = new Intent(getApplicationContext(), ManageBackgroundActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        intent.putExtra("pkgName", q.e);
        startActivity(intent);
    }

    /**
     * Shows a {@link CustomBlocksDialog}.
     */
    public void toCustomBlocks() {
        CustomBlocksDialog.show(this, l);
    }

    /**
     * Opens {@link ExportApkActivity}.
     */
    public void toExportApk() {
        Intent intent = new Intent(getApplicationContext(), ExportApkActivity.class);
        intent.putExtra("scId", l);
        startActivity(intent);
    }

    /**
     * Opens {@link ManageJavaActivity}.
     */
    public void toJava() {
        Intent intent = new Intent(getApplicationContext(), ManageJavaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        intent.putExtra("pkgName", q.e);
        startActivity(intent);
    }

    /**
     * Opens {@link ManageLocalLibraryActivity}.
     */
    public void toLocalLibrary() {
        Intent intent = new Intent(getApplicationContext(), ManageLocalLibraryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        startActivity(intent);
    }

    /**
     * Opens {@link ManageNativelibsActivity}.
     */
    public void toNativelibs() {
        Intent intent = new Intent(getApplicationContext(), ManageNativelibsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        startActivity(intent);
    }

    /**
     * Opens {@link ManagePermissionActivity}.
     */
    public void toPermission() {
        Intent intent = new Intent(getApplicationContext(), ManagePermissionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        startActivity(intent);
    }

    /**
     * Opens {@link ManageProguardActivity}.
     */
    public void toProguard() {
        Intent intent = new Intent(getApplicationContext(), ManageProguardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        startActivity(intent);
    }

    /**
     * Opens {@link ManageResourceActivity}.
     */
    public void toResource() {
        Intent intent = new Intent(getApplicationContext(), ManageResourceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        startActivity(intent);
    }

    /**
     * Opens {@link ManageStringfogActivity}.
     */
    public void toStringfog() {
        Intent intent = new Intent(getApplicationContext(), ManageStringfogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        startActivity(intent);
    }

    /**
     * Opens {@link ManageFontActivity}.
     */
    public void u() {
        Intent intent = new Intent(getApplicationContext(), ManageFontActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        startActivityForResult(intent, 228);
    }

    /**
     * Opens {@link ManageImageActivity}.
     */
    public void v() {
        Intent intent = new Intent(getApplicationContext(), ManageImageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        startActivityForResult(intent, 209);
    }

    /**
     * Opens {@link ManageLibraryActivity}.
     */
    public void w() {
        Intent intent = new Intent(getApplicationContext(), ManageLibraryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        startActivityForResult(intent, 226);
    }

    /**
     * Opens {@link ManageViewActivity}.
     */
    public void x() {
        Intent intent = new Intent(getApplicationContext(), ManageViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        startActivityForResult(intent, 208);
    }

    /**
     * Opens {@link ManageSoundActivity}.
     */
    public void y() {
        Intent intent = new Intent(getApplicationContext(), ManageSoundActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        startActivityForResult(intent, 217);
    }

    /**
     * Opens {@link SrcViewerActivity}.
     */
    public void z() {
        Intent intent = new Intent(getApplicationContext(), SrcViewerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        if (m.getCurrentItem() == 0) {
            try {
                intent.putExtra("current", w.d().getXmlName());
            } catch (Exception ignored) {
            }
        } else if (m.getCurrentItem() != 1) {
            intent.putExtra("current", "");
        } else {
            try {
                intent.putExtra("current", x.d().getJavaName());
            } catch (Exception ignored) {
            }
        }

        startActivityForResult(intent, 240);
    }

    /**
     * Opens {@link DirectEditorActivity}.
     */
    public void zz() {
        Intent intent = new Intent(getApplicationContext(), DirectEditorActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("sc_id", l);
        if (m.getCurrentItem() == 0) {
            try {
                intent.putExtra("current", w.d().getXmlName());
            } catch (Exception ignored) {
            }
        } else if (m.getCurrentItem() != 1) {
            intent.putExtra("current", "");
        } else {
            try {
                intent.putExtra("current", x.d().getJavaName());
            } catch (Exception ignored) {
            }
        }

        startActivityForResult(intent, 240);
    }

    public class a extends MA implements OnCancelListener {

        /**
         * The actual BuildingDialog class
         */
        public Ep c;
        /**
         * Boolean indicating if building got cancelled and we should stop continuing
         */
        public boolean d = false;

        public a(Context context) {
            super(context);
            DesignActivity.this.a((MA) this);
            c = new Ep(DesignActivity.this);
            d();
            c.a(false);
        }

        /**
         * Reverts c (the "Run"-Button) to its original state,
         * usually called after compilation was successful.
         * <p>
         * This closes the dialog, reverts u (the "Run"-Button)'s text and clickable property,
         * and clears the FLAG_KEEP_SCREEN_ON flag.
         */
        @Override
        public void a() {
            q.b();
            c();
            u.setText(xB.b().a(getApplicationContext(), 2131625030));
            u.setClickable(true);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        /**
         * Shows a Toast about APK build having failed, closes the dialog,
         * reverts u (the "Run"-Button) and clears the FLAG_KEEP_SCREEN_ON flag.
         *
         * @param str Ignored parameter, for some reason
         */
        @Override
        public void a(String str) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    q.b();
                    c();
                    bB.b(getApplicationContext(), "APK build failed", Toast.LENGTH_SHORT).show();
                    u.setText(xB.b().a(getApplicationContext(), 2131625030));
                    u.setClickable(true);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
            });
        }

        public void aWithMessage(String message) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    a();
                    bB.b(getApplicationContext(), "APK build failed: " + message, Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        protected void onProgressUpdate(String... values) {
            c(values[0]);
        }

        @Override
        public void b() {
            if (d) {
                cancel(true);
            } else {
                BuildSettings buildSettings = new BuildSettings(q.b);

                try {
                    q.c();
                    q.c(a);
                    q.a();
                    q.a(a, wq.e("600"));
                    if (yB.a(lC.b(l), "custom_icon")) {
                        q.a(wq.e()
                                + File.separator + l
                                + File.separator + "icon.png");
                    }

                    kC kC = jC.d(l);
                    kC.b(q.w + File.separator + "drawable-xhdpi");
                    kC = jC.d(l);
                    kC.c(q.w + File.separator + "raw");
                    kC = jC.d(l);
                    kC.a(q.A + File.separator + "fonts");
                    n();
                    q.f();
                    q.e();
                    Dp mDp = new Dp(this, a, q);
                    publishProgress("Extracting AAPT/AAPT2 binaries...");
                    mDp.i();
                    publishProgress("Extracting built-in libraries...");
                    mDp.j();
                    if (d) {
                        cancel(true);
                        return;
                    }

                    boolean usingAapt2 = buildSettings
                            .getValue(BuildSettings.SETTING_RESOURCE_PROCESSOR,
                                    BuildSettings.SETTING_RESOURCE_PROCESSOR_AAPT
                            ).equals(BuildSettings.SETTING_RESOURCE_PROCESSOR_AAPT2);
                    publishProgress(usingAapt2 ? "AAPT2 is running..." : "AAPT is running...");
                    mDp.a();
                    if (d) {
                        cancel(true);
                        return;
                    }

                    publishProgress("Java is compiling...");

                    boolean incrementalCompilationEnabled = buildSettings.
                            getValue(BuildSettings.SETTING_INCREMENTAL_BUILD_ACTIVE, BuildSettings.SETTING_GENERIC_VALUE_FALSE)
                            .equals(BuildSettings.SETTING_GENERIC_VALUE_TRUE);

                    if (incrementalCompilationEnabled) {
                        IncrementalCompiler incrementalCompiler = new IncrementalCompiler(q);
                        incrementalCompiler.setResultListener(new Compiler.Result() {
                            @Override
                            public void onResult(boolean success, String message) {
                                if (!success) {
                                    DesignActivity.this.d(message);
                                } else {
                                    publishProgress("Building APK...");
                                    mDp.g();

                                    if (d) {
                                        cancel(true);
                                        return;
                                    }

                                    publishProgress("Signing APK...");
                                    mDp.k();
                                    if (d) {
                                        cancel(true);
                                        return;
                                    }

                                    /* Launch Intent to install APK */
                                    o();
                                }
                            }
                        });
                        incrementalCompiler.performCompilation();
                    } else {
                        /* Compile the regular, old way */
                        /* Compile Java classes */
                        mDp.f();
                        if (d) {
                            cancel(true);
                            return;
                        }

                        /* Encrypt Strings in classes if enabled */
                        StringfogHandler stringfogHandler = new StringfogHandler(q.b);
                        stringfogHandler.start(this, mDp);
                        if (d) {
                            cancel(true);
                            return;
                        }

                        /* Obfuscate classes if enabled */
                        ProguardHandler proguardHandler = new ProguardHandler(q.b);
                        proguardHandler.start(this, mDp);
                        if (d) {
                            cancel(true);
                            return;
                        }

                        /* Create DEX file(s) */
                        publishProgress(mDp.getDxRunningText());
                        mDp.c();
                        if (d) {
                            cancel(true);
                            return;
                        }

                        /* Merge DEX file(s) with libraries' dexes */
                        publishProgress("Merging libraries' DEX files...");
                        mDp.h();
                        if (d) {
                            cancel(true);
                            return;
                        }

                        //TODO: Remove this, move AAB export to {@link ExportProjectActivity}
                        boolean buildingAAB = new BuildSettings(q.b)
                                .getValue(BuildSettings.SETTING_OUTPUT_FORMAT,
                                        BuildSettings.SETTING_OUTPUT_FORMAT_APK)
                                .equals(BuildSettings.SETTING_OUTPUT_FORMAT_AAB);
                        if (buildingAAB) {
                            BundleToolCompiler compiler = new BundleToolCompiler(mDp, this);
                            publishProgress("Creating app module...");
                            compiler.createModuleMainArchive();
                            publishProgress("Building app bundle...");
                            compiler.buildBundle();
                            publishProgress("Building APK Set...");
                            compiler.buildApkSet();
                            publishProgress("Extracting Install-APK from APK Set...");
                            compiler.extractInstallApkFromApkSet();
                            publishProgress("Signing Install-APK...");
                            compiler.signInstallApk();
                        } else {
                            publishProgress("Building APK...");
                            mDp.g();
                            if (d) {
                                cancel(true);
                                return;
                            }

                            publishProgress("Signing APK...");
                            if (VERSION.SDK_INT >= 26) {
                                ApkSigner signer = new ApkSigner(a);
                                signer.signWithTestKey(mDp.f.G, mDp.f.H, null);
                            } else {
                                mDp.k();
                            }
                            if (d) {
                                cancel(true);
                                return;
                            }
                        }

                        /* Launch Intent to install APK */
                        o();
                    }
                } catch (Ay e) {
                    //Never thrown? Haven't found a reference to it in any classes except {@link DesignActivity} and {@link PublishActivity} (and of course {@link Ay})
                    Log.e("DesignActivity$a", e.getMessage(), e);
                    //This seems kinda odd
                    c(e.getMessage());
                } catch (OutOfMemoryError error) {
                    Log.e("DesignActivity$a", "OutOfMemoryError: " + error.getMessage(), error);
                    System.gc();
                    DesignActivity.this.d(error.getMessage());
                    //throw new By(xB.b().a(super.a, 2131624953));
                } catch (Throwable e) {
                    Log.e("DesignActivity$a", Log.getStackTraceString(e), e);
                    DesignActivity.this.d(e.getMessage());
                }
            }
        }

        /**
         * Dismiss this dialog, if DesignActivity hasn't been destroyed.
         */
        public void c() {
            if (!isDestroyed()) {
                if (c.isShowing()) {
                    c.dismiss();
                }
            }
        }

        /**
         * Updates the dialog's progress text.
         *
         * @param progressText The new text to display as progress
         */
        public void c(String progressText) {
            runOnUiThread(new DesignActRunnable(c, progressText));
        }

        /**
         * Try to set this dialog's OnCancelListener as this, then show, unless already showing.
         */
        public void d() {
            if (!c.isShowing()) {
                c.setOnCancelListener(this);
                c.show();
            }
        }

        @Override
        public void onCancel(DialogInterface dialog) {
            if (!c.a()) {
                c.a(true);
                d();
                publishProgress("Canceling build...");
                d = true;
            }
        }

        @Override
        public void onCancelled() {
            super.onCancelled();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    u.setText(xB.b().a(getApplicationContext(), Resources.string.common_word_run));
                    u.setClickable(true);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
            });
            q.b();
            c();
        }

        @Override
        protected String doInBackground(Void... voids) {
            return a(voids);
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            u.setText("Building APK file...");
            u.setClickable(false);
            r.a("P1I10", true);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    public class b extends MA {

        public Bundle c;

        public b(Context var2, Bundle var3) {
            super(var2);
            DesignActivity.this.a(this);
            c = var3;
        }

        public void a() {
            Bundle var1 = this.c;
            if (var1 != null) {
                DesignActivity.this.v.a(var1);
                if (this.c.getInt("file_selector_current_file_type") == 0) {
                    DesignActivity.this.A.setVisibility(View.VISIBLE);
                } else {
                    DesignActivity.this.A.setVisibility(View.GONE);
                }
            }

            DesignActivity.this.v.a();
            DesignActivity.this.h();
            if (this.c == null) {
                DesignActivity.this.l();
            }

        }

        public void a(String var1) {
            DesignActivity.this.h();
        }

        public void b() {
            DesignActivity.this.a(this.c != null);

        }

        @Override
        protected String doInBackground(Void... voids) {
            return a(voids);
        }
    }

    /**
     * A project "saver" AsyncTask that doesn't actually save the project?
     * Gets executed when clicking "Exit" in the "Save project?" dialog.
     */
    public class c extends MA {

        public c(Context var2) {
            super(var2);
            DesignActivity.this.a(this);
        }

        public void a() {
            DesignActivity.this.h();
            DesignActivity.this.finish();
        }

        public void a(String var1) {
            DesignActivity.this.h();
            DesignActivity.this.finish();
        }

        public void b() {
            publishProgress("Now processing..");
            jC.d(DesignActivity.this.l).v();
            jC.d(DesignActivity.this.l).w();
            jC.d(DesignActivity.this.l).u();
        }

        @Override
        protected String doInBackground(Void... voids) {
            return a(voids);
        }
    }

    /**
     * An AsyncTask saving the project.
     */
    public class d extends MA {

        public d(Context var2) {
            super(var2);
            DesignActivity.this.a(this);
        }

        public void a() {
            bB.a(super.a, xB.b().a(super.a, 2131624938), 0).show();
            DesignActivity.this.A();
            DesignActivity.this.h();
            jC.d(DesignActivity.this.l).f();
            jC.d(DesignActivity.this.l).g();
            jC.d(DesignActivity.this.l).e();
        }

        public void a(String var1) {
            bB.b(super.a, xB.b().a(super.a, 2131624915), 0).show();
            DesignActivity.this.h();
        }

        public void b() {
            publishProgress("Now saving..");
            jC.d(DesignActivity.this.l).a();
            jC.b(DesignActivity.this.l).m();
            jC.a(DesignActivity.this.l).j();
            jC.d(DesignActivity.this.l).x();
            jC.c(DesignActivity.this.l).l();
        }

        @Override
        protected String doInBackground(Void... voids) {
            return a(voids);
        }
    }

    public class e extends MA {

        public e(Context var2) {
            super(var2);
            DesignActivity.this.a(this);
        }

        public void a() {
            bB.a(super.a, xB.b().a(super.a, 2131624938), 0).show();
            DesignActivity.this.A();
            DesignActivity.this.h();
            DesignActivity.this.finish();
        }

        public void a(String var1) {
            bB.b(super.a, xB.b().a(super.a, 2131624915), 0).show();
            DesignActivity.this.h();
        }

        public void b() {
            publishProgress("Now saving..");
            jC.d(DesignActivity.this.l).a();
            jC.b(DesignActivity.this.l).m();
            jC.a(DesignActivity.this.l).j();
            jC.d(DesignActivity.this.l).x();
            jC.c(DesignActivity.this.l).l();
            jC.d(DesignActivity.this.l).h();
        }

        @Override
        protected String doInBackground(Void... voids) {
            return a(voids);
        }
    }

    public class f extends MA {

        public f(Context var2) {
            super(var2);
            DesignActivity.this.a(this);
        }

        public void a() {
        }

        public void a(String var1) {
        }

        public void b() {
            jC.a(DesignActivity.this.l).k();
        }

        @Override
        protected String doInBackground(Void... voids) {
            return a(voids);
        }

        public void onCancelled() {
            super.onCancelled();
        }
    }

    public class g extends gg {

        public final int f = 3;
        public String[] g;
        public Context h;

        public g(Xf var2, Context var3) {
            super(var2);
            h = var3;
            g = new String[3];
            g[0] = xB.b().a(var3, 2131625319);
            g[1] = xB.b().a(var3, 2131625318);
            g[2] = xB.b().a(var3, 2131625317);
        }

        public int a() {
            return 3;
        }

        public CharSequence a(int var1) {
            return this.g[var1];
        }

        public Object a(ViewGroup var1, int var2) {
            Fragment var3 = (Fragment) super.a(var1, var2);
            if (var2 == 0) {
                DesignActivity.this.w = (jr) var3;
            } else if (var2 == 1) {
                DesignActivity.this.x = (rs) var3;
            } else {
                DesignActivity.this.y = (br) var3;
            }

            return var3;
        }

        public Fragment c(int var1) {
            if (var1 == 0) {
                return new jr();
            } else {
                return var1 == 1 ? new rs() : new br();
            }
        }
    }
}
