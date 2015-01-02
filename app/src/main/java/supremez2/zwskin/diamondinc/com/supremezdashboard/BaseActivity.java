package supremez2.zwskin.diamondinc.com.supremezdashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.support.v7.widget.CardView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;


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