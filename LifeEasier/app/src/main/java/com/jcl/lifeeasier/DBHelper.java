package com.jcl.lifeeasier;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context)
    {
        super(context,"myDB",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String table1="CREATE TABLE notes (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"name TEXT,status BOOLEAN)";
        db.execSQL(table1);
        String table2="CREATE TABLE expenses (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"reason TEXT, amount DOUBLE )";
        db.execSQL(table2);
        String table3="CREATE TABLE tasks (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"name TEXT,fromtime TEXT ,totime TEXT,status BOOLEAN)";
        db.execSQL(table3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
