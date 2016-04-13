package com.example.stiles.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by stiles on 16/4/12.
 */
public class AddClass extends Activity {
    private Button add_detail_btn;
    private LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_class);
        add_detail_btn = (Button)findViewById(R.id.add_detail_btn);
        container = (LinearLayout)findViewById(R.id.container);
        add_detail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                container.addView(container);
            }
        });
    }
}
