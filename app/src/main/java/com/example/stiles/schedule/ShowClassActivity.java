package com.example.stiles.schedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.stiles.model.Class;
import com.example.stiles.service.ClassService;
import org.w3c.dom.Text;

/**
 * Created by stiles on 16/4/15.
 */
public class ShowClassActivity extends Activity {
    private static final String[] weeks = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
    private int id;
    private ClassService classService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_class);
        classService = new ClassService(getBaseContext());
        Button del_btn = (Button) findViewById(R.id.del_btn);
        Intent intent = getIntent();
        id = intent.getIntExtra("id" , -1);
        TextView show_class_name = (TextView) findViewById(R.id.show_class_name);
        TextView show_teacher_name = (TextView) findViewById(R.id.show_teacher_name);
        TextView show_place = (TextView) findViewById(R.id.show_place);
        TextView show_time = (TextView) findViewById(R.id.show_time);
        Class c = classService.findById(id);
        show_class_name.setText("课程名称: " + c.getClass_name());
        show_teacher_name.setText("任课教师: " + c.getTeacher_name());
        show_place.setText("课程地点: " + c.getClassroom());
        String time = "课程时间:\n\n";
        time += weeks[c.getWeek()];
        int cur = c.getStart()+1;
        for (int i = 0; i < c.getLength(); i++, cur++) {
            time += " " + cur;
        }
        time += "节";
        show_time.setText(time);

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classService.deleteById(id);
                ShowClassActivity.this.onBackPressed();
                overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom);
            }
        });
    }


}
