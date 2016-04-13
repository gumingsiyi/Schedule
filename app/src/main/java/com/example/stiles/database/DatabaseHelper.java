package com.example.stiles.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stiles on 16/4/12.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    String DATABASENAME = "schedule.db";
    private String TABLENAME = "class";
    private String ID = "id";
    private String CLASSNAME = "class_name";
    private String WEEK = "week";
    private String START = "start";
    private String LENGTH = "length";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists " + TABLENAME + "(" +
                ID + " integer primary key autoincrement, " +
                CLASSNAME + " text, " +
                WEEK + " integer, " +
                START + " integer, " +
                LENGTH + " integer);";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
