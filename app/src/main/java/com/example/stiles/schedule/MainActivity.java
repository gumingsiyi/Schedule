package com.example.stiles.schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    static final int ADD_CLASS_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout[] week = new LinearLayout[7];

        week[1] = (LinearLayout) findViewById(R.id.mon);
        week[2] = (LinearLayout) findViewById(R.id.tue);
        week[3] = (LinearLayout) findViewById(R.id.wed);
        week[4] = (LinearLayout) findViewById(R.id.thu);
        week[5] = (LinearLayout) findViewById(R.id.fri);
        week[6] = (LinearLayout) findViewById(R.id.sat);
        week[0] = (LinearLayout) findViewById(R.id.sun);

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
        if (id == R.id.exit) {
            finish();
            return true;
        } else if (id == R.id.add) {
            Intent intent = new Intent();
            intent.setClass(getBaseContext(), AddClass.class);
            startActivityForResult(intent, ADD_CLASS_CODE);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                
            }
        }
    }
}
