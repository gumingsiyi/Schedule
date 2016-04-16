package com.example.stiles.schedule;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.stiles.model.Class;
import com.example.stiles.service.ClassService;

import java.util.ArrayList;
/**
 * Created by stiles on 16/4/12.
 */
public class AddClass extends Activity {
    private LinearLayout container;
    private static final String[] weeks = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
    private static final String[] start = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    private static final String[] length = {"1", "2", "3", "4", "5"};
    private ArrayList<LinearLayout> list;
    private ClassService classService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_class);
        classService = new ClassService(getBaseContext());
        Button add_detail_btn = (Button) findViewById(R.id.add_detail_btn);
        container = (LinearLayout)findViewById(R.id.container);
        list = new ArrayList<>();
        add_detail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout temp = createLayout();
                container.addView(temp);
            }
        });

        Button submit_btn = (Button) findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //读取所有课程细节信息,存入数据库
                if (list.size() == 0 || list == null) {
                    Toast.makeText(AddClass.this, "请输入课程信息", Toast.LENGTH_LONG).show();
                } else {
                    //得到课程信息,储存到数据库
                    saveClassInfo();
                    AddClass.this.onBackPressed();
                }
            }
        });
    }

    private LinearLayout createLayout() {
        LinearLayout res = new LinearLayout(getBaseContext());
        res.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 20, 0, 0);
        res.setLayoutParams(params);

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

        Button del_btn = createDelButton();

        res.addView(del_btn, new ViewGroup.LayoutParams(100, 100));
        res.addView(spinner_week, layoutParamsWrap);
        res.addView(textView1, layoutParamsWrap);
        res.addView(spinner_start, layoutParamsWrap);
        res.addView(textView2, layoutParamsWrap);
        res.addView(spinner_length, layoutParamsWrap);
        res.addView(textView3, layoutParamsWrap);
        res.addView(editText, layoutParamsMatch);

        list.add(res);

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

    private Button createDelButton() {
        Button button = new Button(getBaseContext());
        button.setBackgroundResource(R.drawable.btn_shape_red);
        button.setText("X");
        button.setTextColor(Color.WHITE);
        button.setTextSize(12);
        button.setGravity(Gravity.CENTER);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout temp = list.get(list.size()-1);
                list.remove(list.size()-1);
                container.removeView(temp);
            }
        });
        return button;
    }

    private void saveClassInfo() {
        Class c;
        c = new Class();
        EditText editText = (EditText) findViewById(R.id.class_name);
        String class_name = editText.getText().toString();
        editText = (EditText) findViewById(R.id.teacher_name);
        String teacher_name = editText.getText().toString();

        c.setClass_name(class_name);
        c.setTeacher_name(teacher_name);

        for (LinearLayout curLayout : list) {
            Spinner spinner = (Spinner)curLayout.getChildAt(1);//取得星期
            int week = spinner.getSelectedItemPosition();
            spinner = (Spinner)curLayout.getChildAt(3);//取得开始
            int start = spinner.getSelectedItemPosition();
            spinner = (Spinner)curLayout.getChildAt(5);
            int length = spinner.getSelectedItemPosition();
            EditText et = (EditText)curLayout.getChildAt(7);
            String classroom = et.getText().toString();
            //Toast.makeText(AddClass.this, String.valueOf(week), Toast.LENGTH_LONG).show();
            c.setWeek(week);
            c.setStart(start);
            c.setLength(length + 1);
            c.setClassroom(classroom);
            //储存进数据库
            classService.save(c);
        }
    }
}
