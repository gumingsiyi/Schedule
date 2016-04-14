package com.example.stiles.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stiles on 16/4/12.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public final static String DATABASENAME = "schedule.db";
    public final static String TABLENAME = "class";
    public final static String ID = "id";
    public final static String CLASSNAME = "class_name";
    public final static String TEACHERNAME = "teacher_name";
    public final static String CLASSROOM = "classroom";
    public final static String WEEK = "week";
    public final static String START = "start";
    public final static String LENGTH = "length";
    public final static int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists " + TABLENAME + " (" +
                ID + " integer primary key autoincrement, " +
                CLASSNAME + " text, " +
                TEACHERNAME + " text, " +
                CLASSROOM + " text, " +
                WEEK + " integer, " +
                START + " integer, " +
                LENGTH + " integer);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
