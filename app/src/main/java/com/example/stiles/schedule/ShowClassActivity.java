package com.example.stiles.schedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;
import com.example.stiles.model.Class;

/**
 * Created by stiles on 16/4/15.
 */
public class ShowClassActivity extends Activity {
    Button del_btn;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_class);
        del_btn = (Button) findViewById(R.id.del_btn);
        Intent intent = getIntent();
        id = intent.getIntExtra("id" , -1);

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowClassActivity.this.onBackPressed();
            }
        });
    }


}
