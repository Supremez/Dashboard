package com.antonioleiva.materialeverywhere;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.antonioleiva.materialeverywhere.uk.co.senab.photoview.PhotoViewAttacher;
import com.soundcloud.android.crop.Crop;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.supremez.dashboard.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class DetailActivity extends BaseActivity {

    ImageView mImageView;
    PhotoViewAttacher mAttacher;
    ProgressBar progressBar;

    public static final String EXTRA_IMAGE = "DetailActivity:image";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImageView = (ImageView) findViewById(R.id.image);
        progressBar = (ProgressBar) findViewById(R.id.loading);
        mAttacher = new PhotoViewAttacher(mImageView);


        ViewCompat.setTransitionName(mImageView, EXTRA_IMAGE);
        Picasso.with(this).load(getIntent().getStringExtra(EXTRA_IMAGE)).into(mImageView, new Callback.EmptyCallback() {
            @Override public void onSuccess() {
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onError() {
                progressBar.setVisibility(View.GONE);
            }


        });
    }



    @Override protected int getLayoutResource() {
        return R.layout.activity_detail;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wallpaper, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.set_wall) {

            mImageView = (ImageView) findViewById(R.id.image);

            Bitmap bmp = getImageBitmap();
            OutputStream stream = null;
            try {
                stream = new FileOutputStream("/sdcard/test.png");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bmp.compress(Bitmap.CompressFormat.PNG, 0, stream);





            Uri tempUri = Uri.parse("file:///sdcard/test.png");



            beginCrop(tempUri);



        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            beginCrop(result.getData());
        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, result);
        }
    }

    private void beginCrop(Uri source) {
        Uri outputUri = Uri.parse("file:///sdcard/test.png");
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displaymetrics);

        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        Crop.of(source, outputUri).withMaxSize(width,height).start(this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {


            mImageView.setImageURI(Crop.getOutput(result));

            WallpaperManager myWallpaperManager = WallpaperManager
                    .getInstance(getApplicationContext());

            try {

                Bitmap mBitmap = getImageBitmap();
                myWallpaperManager.setBitmap(mBitmap);
                Toast.makeText(DetailActivity.this, "Wallpaper set",
                        Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                Toast.makeText(DetailActivity.this,
                        "Error setting wallpaper", Toast.LENGTH_SHORT)
                        .show();
            }

        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public static void launch(BaseActivity activity, View transitionView, String url) {
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, transitionView, EXTRA_IMAGE);
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(EXTRA_IMAGE, url);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    public Bitmap getImageBitmap () {
        try {
            final Drawable drawable = this.mImageView.getDrawable();
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }

            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            Log.e(EXTRA_IMAGE, "", e);
            return null;
        }
    }




}