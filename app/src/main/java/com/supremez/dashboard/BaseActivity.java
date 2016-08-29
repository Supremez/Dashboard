package com.supremez.dashboard;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ScrollView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.melnykov.fab.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public abstract class BaseActivity extends ActionBarActivity {

    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            YoYo.with(Techniques.RotateIn)
                    .duration(600)
                    .playOn(findViewById(R.id.imageView4));

            YoYo.with(Techniques.FadeIn)
                    .duration(1800)
                    .playOn(findViewById(R.id.imageView3));

            YoYo.with(Techniques.Shake)
                    .delay(800)
                    .interpolate(new AccelerateDecelerateInterpolator())
                    .duration(2100)
                    .playOn(findViewById(R.id.imageView3));

        }


        ScrollView scrollView = (ScrollView) findViewById(R.id.scroll);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.attachToScrollView((com.melnykov.fab.ObservableScrollView) scrollView);

        fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                //For example: Start Wallpaper Home Activity
                Intent intent = new Intent(v.getContext(), com.antonioleiva.materialeverywhere.HomeActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void wallpaper(View view) {
        Intent intent = new Intent(this, com.antonioleiva.materialeverywhere.HomeActivity.class);
        startActivity(intent);
    }

    public void onClick(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.playstore_link))));

    }

    public void gplus(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.googleplus_link))));

    }

    public void getzooper(View view)

    {
        final AlertDialog.Builder menuAleart = new AlertDialog.Builder(this);
        final String[] menuList = {"AMAZON APP STORE", "GOOGLE PLAY STORE"};
        menuAleart.setItems(menuList, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        boolean installed = appInstalledOrNot("com.amazon.venezia");
                        if (installed) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("amzn://apps/android?p=org.zooper.zwpro")));
                        } else {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=org.zooper.zwpro")));
                        }
                        break;
                    case 1:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=org.zooper.zwpro")));
                        break;
                }
            }
        });
        AlertDialog menuDrop = menuAleart.create();
        menuDrop.show();
    }

    public void iconsets(View view) {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("IconSets");
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        for (String filename : files) {
            System.out.println("File name => " + filename);
            InputStream in;
            OutputStream out;
            try {
                in = assetManager.open("IconSets/" + filename);   // if files resides inside the "fonts" directory itself
                out = new FileOutputStream(Environment.getExternalStorageDirectory().toString() + "/ZooperWidget/IconSets/" + filename);
                copyFile(in, out);
                in.close();
                out.flush();
                out.close();
            } catch (Exception e) {
                Log.e("tag", e.getMessage());
            }
            Toast.makeText(this, "Iconsets installed successfully", Toast.LENGTH_SHORT).show();
        }
    }


    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }


    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    private void showInstallableSkins() {
        if (isSDcardAvailable()) {
            new RepairSkinAsyncTask().execute();
        } else {
            Toast.makeText(this, "SD card not available", Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void deleteOldSkin(String pathToSkin) {
        File file = new File(pathToSkin);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * @param assetManager
     * @param in
     * @param out
     * @param pathToSkin
     */
    private void saveSkinToSdCard(String pathToSkin) {
        AssetManager assetManager = getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(getString(R.string.apkfilename));
            try {
                out = new FileOutputStream(pathToSkin);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param pathToSkin
     */
    private void startAppInstaller(String pathToSkin) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(pathToSkin)),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private boolean isSDcardAvailable() {
        String state = Environment.getExternalStorageState();
        return state.contentEquals(Environment.MEDIA_MOUNTED)
                || state.contentEquals(Environment.MEDIA_MOUNTED_READ_ONLY);
    }

    protected abstract int getLayoutResource();

    private class RepairSkinAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... nothing) {
            String SDCARD_MYAPK_APK = Environment.getExternalStorageDirectory()
                    .getPath() + File.separator + "temporary92384534$.apk";
            deleteOldSkin(SDCARD_MYAPK_APK);
            saveSkinToSdCard(SDCARD_MYAPK_APK);
            startAppInstaller(SDCARD_MYAPK_APK);
            return null;
        }
    }


}