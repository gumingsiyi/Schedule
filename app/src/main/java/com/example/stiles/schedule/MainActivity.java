package com.example.stiles.schedule;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.example.stiles.database.DatabaseHelper;
import com.example.stiles.model.Class;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_CLASS_CODE = 1;
    private RelativeLayout[] week = new RelativeLayout[7];
    private int per_height;
    private int height;
    private int width;
    private boolean hasMeasured = false;
    int color_flag = 1;
    private ArrayList<Class> classList;
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
                    //Toast.makeText(MainActivity.this, String.valueOf(height), Toast.LENGTH_LONG).show();
                    drawClass();
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
    //从数据库中得到课程信息,生成视图.
    private void drawClass() {
        for (int i = 0; i < 7; i++) {
            week[i].removeAllViews();
        }
        setClassList();
        if (classList != null) {
            for (Class cur: classList) {
                RelativeLayout class_layout = createClassLayout(cur.getStart(), cur.getLength());
                //class_layout.setOrientation(LinearLayout.VERTICAL);

                TextView class_text = createClassInfo(cur.getClass_name()+"\n /@ "+cur.getClassroom());

                class_text.setGravity(Gravity.TOP | Gravity.LEFT);

                Button id_btn = createIdBtn(cur.id);
                class_layout.addView(id_btn);
                class_layout.addView(class_text);

                week[cur.getWeek()].addView(class_layout);
            }
        }
    }

    private void setClassList() {
        classList = new ArrayList<>();
        DatabaseHelper helper = new DatabaseHelper(getBaseContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLENAME, null, null, null, null, null, null);

        int idIndex = cursor.getColumnIndex(DatabaseHelper.ID);
        int classNameIndex = cursor.getColumnIndex(DatabaseHelper.CLASSNAME);
        int teacherNameIndex = cursor.getColumnIndex(DatabaseHelper.TEACHERNAME);
        int classroomIndex = cursor.getColumnIndex(DatabaseHelper.CLASSROOM);
        int weekIndex = cursor.getColumnIndex(DatabaseHelper.WEEK);
        int startIndex = cursor.getColumnIndex(DatabaseHelper.START);
        int lengthIndex = cursor.getColumnIndex(DatabaseHelper.LENGTH);

        int id;
        String className;
        String teacherName;
        String classroom;
        int week;
        int start;
        int length;

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            id = cursor.getInt(idIndex);
            className = cursor.getString(classNameIndex);
            teacherName = cursor.getString(teacherNameIndex);
            classroom = cursor.getString(classroomIndex);
            week = cursor.getInt(weekIndex);
            start = cursor.getInt(startIndex);
            length = cursor.getInt(lengthIndex);

            Class c = new Class(className, teacherName, classroom, week, start, length);
            c.id = id;
            classList.add(c);
        }

        cursor.close();
    }

    private RelativeLayout createClassLayout(int start, int length) {
        RelativeLayout layout = new RelativeLayout(getBaseContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, length * per_height-2);
        if (color_flag == 1) {
            layout.setBackgroundResource(R.drawable.background_pink);
        } else {
            layout.setBackgroundResource(R.drawable.bacckground_lightpurple);
        }
        color_flag = 1 - color_flag;

        params.setMargins(2, start*per_height+2, 2, 2);
        layout.setLayoutParams(params);
        return layout;
    }

    private Button createIdBtn(int id) {
        Button btn = new Button(getBaseContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        btn.setText(String.valueOf(id));
        btn.setAlpha(0);
        btn.setLayoutParams(params);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this,"hhhh",Toast.LENGTH_LONG).show();
            }
        });
        return btn;
    }

    private TextView createClassInfo(String string) {
        TextView textView = new TextView(getBaseContext());

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        textView.setText(string);
        textView.setTextColor(Color.BLACK);
        textView.setLayoutParams(params);
        return textView;
    }
}
