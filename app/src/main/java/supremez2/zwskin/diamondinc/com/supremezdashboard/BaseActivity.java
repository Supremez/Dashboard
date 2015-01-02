package supremez2.zwskin.diamondinc.com.supremezdashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.support.v7.widget.CardView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;
import com.nineoldandroids.animation.Animator;

import java.util.Observable;


public abstract class BaseActivity extends ActionBarActivity {

    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            YoYo.with(Techniques.Shake)
                    .duration(2100)
                    .playOn(findViewById(R.id.imageView3));


        }


        ScrollView scrollView = (ScrollView) findViewById(R.id.scroll);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //TODO Attatch FAB to ScrollView
        //fab.attachToScrollView(scrollView);
       fab.setOnClickListener(new View.OnClickListener() {

           public void onClick(View v) {
               //TODO Add click action

           }
       });

    }

    public void wallpaper(View view) {
      Intent intent = new Intent(this, com.antonioleiva.materialeverywhere.HomeActivity.class);
        startActivity(intent);
    }


    protected abstract int getLayoutResource();


    protected void setActionBarIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
    }

}