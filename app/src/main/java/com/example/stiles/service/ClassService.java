package com.example.stiles.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.stiles.database.DatabaseHelper;
import com.example.stiles.model.Class;

import java.util.ArrayList;

/**
 * Created by stiles on 16/4/16.
 */
public class ClassService {
    private SQLiteDatabase Rdb;
    private SQLiteDatabase Wdb;
    public ClassService(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        Rdb = helper.getReadableDatabase();
        Wdb = helper.getWritableDatabase();
    }
    public void save(Class c) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.CLASSNAME, c.getClass_name());
        values.put(DatabaseHelper.TEACHERNAME, c.getTeacher_name());
        values.put(DatabaseHelper.CLASSROOM, c.getClassroom());
        values.put(DatabaseHelper.WEEK, c.getWeek());
        values.put(DatabaseHelper.START, c.getStart());
        values.put(DatabaseHelper.LENGTH, c.getLength());
        Wdb.insert(DatabaseHelper.TABLENAME, "id", values);
    }
    public ArrayList<Class> findAll() {
        ArrayList<Class> list = new ArrayList<>();
        Cursor cursor = Rdb.query(DatabaseHelper.TABLENAME, null, null, null, null, null, null);
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
            list.add(c);
        }

        cursor.close();
        return list;
    }

}
