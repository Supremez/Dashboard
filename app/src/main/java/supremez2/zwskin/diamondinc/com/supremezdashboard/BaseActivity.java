package supremez2.zwskin.diamondinc.com.supremezdashboard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ScrollView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.melnykov.fab.FloatingActionButton;


public abstract class BaseActivity extends ActionBarActivity {

    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(getLayoutResource());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

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
               Intent intent=new Intent(v.getContext(),com.antonioleiva.materialeverywhere.HomeActivity.class);
               startActivity(intent);

           }
       });

    }


    public void wallpaper(View view) {
      Intent intent = new Intent(this, com.antonioleiva.materialeverywhere.HomeActivity.class);
        startActivity(intent);
    }

    public void onClick(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.playstore_link))));

    }


    protected abstract int getLayoutResource();


    protected void setActionBarIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
    }

}