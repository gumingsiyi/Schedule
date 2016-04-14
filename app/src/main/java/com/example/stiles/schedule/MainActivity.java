package com.example.stiles.schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_CLASS_CODE = 1;
    private RelativeLayout[] week = new RelativeLayout[7];
    private int per_height;
    private int height;
    private int width;
    private boolean hasMeasured = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LinearLayout main_layout = (LinearLayout)findViewById(R.id.main_layout);

        week[0] = (RelativeLayout) findViewById(R.id.mon);
        week[1] = (RelativeLayout) findViewById(R.id.tue);
        week[2] = (RelativeLayout) findViewById(R.id.wed);
        week[3] = (RelativeLayout) findViewById(R.id.thu);
        week[4] = (RelativeLayout) findViewById(R.id.fri);
        week[5] = (RelativeLayout) findViewById(R.id.sat);
        week[6] = (RelativeLayout) findViewById(R.id.sun);

        ViewTreeObserver vto = main_layout.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (!hasMeasured) {
                    height = week[0].getMeasuredHeight();
                    width = week[0].getMeasuredWidth();
                    per_height = height/12;
                    hasMeasured = true;
                    Toast.makeText(MainActivity.this, String.valueOf(height), Toast.LENGTH_LONG).show();

                }
                return true;
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
        if (requestCode == ADD_CLASS_CODE) {
            if (resultCode == RESULT_OK) {
                
            }
        }
    }
}
