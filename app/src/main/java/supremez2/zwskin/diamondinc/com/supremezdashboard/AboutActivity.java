package supremez2.zwskin.diamondinc.com.supremezdashboard;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class AboutActivity extends ActionBarActivity {

    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            YoYo.with(Techniques.FadeIn)
                    .delay(400)
                    .interpolate(new AccelerateDecelerateInterpolator())
                    .duration(1700)
                    .playOn(findViewById(R.id.card_view1));
            YoYo.with(Techniques.FadeIn)
                    .delay(400)
                    .interpolate(new AccelerateDecelerateInterpolator())
                    .duration(1700)
                    .playOn(findViewById(R.id.card_view2));
        }

        ImageView plustim = (ImageView) findViewById(R.id.gplus_tim);
        plustim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://google.com/+TimBremer"));
                startActivity(viewIntent);
            }
        });


        ImageView gittim = (ImageView) findViewById(R.id.github_tim);
        gittim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://github.com/tbremer19"));
                startActivity(viewIntent);
            }
        });


        ImageView mailtim = (ImageView) findViewById(R.id.mail_tim);
        mailtim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","tbremer19@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SUBJECT");
                startActivity(Intent.createChooser(emailIntent, "Choose email app"));
            }
        });


        ImageView twittertim = (ImageView) findViewById(R.id.twitter_tim);
        twittertim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://twitter.com/tbremer19"));
                startActivity(viewIntent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.changelog:

                final Dialog popup = new Dialog(this);

                popup.requestWindowFeature(Window.FEATURE_NO_TITLE);

                popup.setContentView(R.layout.dialog);

                TextView text1 = (TextView) popup.findViewById(R.id.text1);
                text1.setText(getString(R.string.changelog_title));

                TextView text2 = (TextView) popup.findViewById(R.id.text2);
                text2.setText(getString(R.string.changelog));

                popup.show();

                Button closebutton = (Button) popup.findViewById(R.id.button2);
                closebutton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Close dialog
                        popup.dismiss();
                    }
                });
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
