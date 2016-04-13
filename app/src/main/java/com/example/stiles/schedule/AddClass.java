package com.example.stiles.schedule;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created by stiles on 16/4/12.
 */
public class AddClass extends Activity {
    private Button add_detail_btn;
    private LinearLayout container;
    private static final String[] weeks = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
    private static final String[] start = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    private static final String[] length = {"1", "2", "3", "4", "5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_class);
        add_detail_btn = (Button)findViewById(R.id.add_detail_btn);
        container = (LinearLayout)findViewById(R.id.container);
        add_detail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout temp = createLayout();
                container.addView(temp);
            }
        });
    }

    private LinearLayout createLayout() {
        LinearLayout res = new LinearLayout(getBaseContext());
        res.setOrientation(LinearLayout.HORIZONTAL);


        ArrayAdapter<String> adapter_week = new ArrayAdapter<>(this, R.layout.spinner_item, weeks);
        adapter_week.setDropDownViewResource(R.layout.dropdown);
        ArrayAdapter<String> adapter_length = new ArrayAdapter<>(this, R.layout.spinner_item, length);
        adapter_length.setDropDownViewResource(R.layout.dropdown);
        ArrayAdapter<String> adapter_start = new ArrayAdapter<>(this, R.layout.spinner_item, start);
        adapter_start.setDropDownViewResource(R.layout.dropdown);


        Spinner spinner_week = createSpinner();
        spinner_week.setAdapter(adapter_week);
        Spinner spinner_start = createSpinner();
        spinner_start.setAdapter(adapter_start);
        Spinner spinner_length = createSpinner();
        spinner_length.setAdapter(adapter_length);

        EditText editText = new EditText(getBaseContext());
        editText.setBackgroundResource(R.drawable.edit_gery);
        editText.setAlpha(0.75f);
        editText.setTextColor(Color.rgb(0,0,0));
        editText.setTextSize(15f);

        ViewGroup.LayoutParams layoutParamsWrap = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ViewGroup.LayoutParams layoutParamsMatch = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TextView textView1 = createText("从第");
        TextView textView2 = createText("节 上");
        TextView textView3 = createText("节课 教室");

        res.addView(spinner_week, layoutParamsWrap);
        res.addView(textView1, layoutParamsWrap);
        res.addView(spinner_start, layoutParamsWrap);
        res.addView(textView2, layoutParamsWrap);
        res.addView(spinner_length, layoutParamsWrap);
        res.addView(textView3, layoutParamsWrap);
        res.addView(editText, layoutParamsMatch);

        return res;
    }

    private Spinner createSpinner() {
        Spinner spinner = new Spinner(getBaseContext());
        spinner.setBackgroundResource(R.drawable.edit_gery);
        spinner.setAlpha(0.75f);
        return spinner;
    }

    private TextView createText(String string) {
        TextView textView = new TextView(getBaseContext());
        textView.setText(string);
        textView.setTextColor(Color.rgb(255,255,255));
        textView.setShadowLayer(2,5,5,Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
