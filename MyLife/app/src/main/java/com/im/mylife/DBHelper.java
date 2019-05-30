package com.im.mylife;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

   public DBHelper(Context context)
   {
       super(context,"mydb",null,2);
   }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String table1="CREATE TABLE tasks (id INTEGER PRIMARY KEY AUTOINCREMENT,"+"name TEXT, date DATE , status BOOLEAN)";
       db.execSQL(table1);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

       if(oldVersion==1&&newVersion==2)
       {
           String table2="CREATE TABLE expense (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"name TEXT, date DATE ,category TEXT,amount TEXT,date TEXT)";
           db.execSQL(table2);
       }
    }
}
