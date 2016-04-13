package com.example.stiles.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stiles on 16/4/12.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    String DATABASENAME = "schedule.db";
    String TABLENAME = "class";
    String ID = "id";
    String CLASSNAME = "class_name";


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
