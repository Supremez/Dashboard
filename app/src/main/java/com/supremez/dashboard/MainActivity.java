package com.supremez.dashboard;

import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    //TODO Theme tutorial

    @Override
    protected int getLayoutResource() {

        //return R.layout.activity_main_light;
        return R.layout.activity_main;

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

        if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
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
