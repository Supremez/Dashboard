package supremez2.zwskin.diamondinc.com.supremezdashboard;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivityLight extends BaseActivity {

    //TODO Theme tutorial

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main_light;
        //return R.layout.activity_main;
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
        item.setChecked(false);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_about) {
            Intent intent = new Intent(this, supremez2.zwskin.diamondinc.com.supremezdashboard.AboutActivity.class);
            startActivity(intent);
            return true;
        }

        switch (item.getItemId()) {
            case R.id.LightTheme:
                if (id == R.id.LightTheme) item.setChecked(true); {
                    Intent intent = new Intent(this, supremez2.zwskin.diamondinc.com.supremezdashboard.MainActivityLight.class);
                    startActivity(intent);
                    break;
                }

            case R.id.DarkTheme:
                if (id == R.id.DarkTheme) item.setChecked(true); {
                    Intent intent = new Intent(this, supremez2.zwskin.diamondinc.com.supremezdashboard.MainActivity.class);
                    startActivity(intent);
                    break;
                }
        }



        return super.onOptionsItemSelected(item);

    }
}