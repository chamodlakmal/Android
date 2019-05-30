package com.im.mylife;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExpencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expences);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ExpencesActivity.this,ExpenceAddActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        //List<String> list=new ArrayList<String>();
        String sql="SELECT * from expense";
        DBHelper myDB=new DBHelper(this);
        SQLiteDatabase db=myDB.getReadableDatabase();

        Cursor cursor=db.rawQuery(sql,null);
        /*cursor.moveToFirst();
        while(cursor.isAfterLast()==false)
        {
            String taskName=cursor.getString(1);
            list.add(taskName);
            cursor.moveToNext();
        }*/
        ListView lv=findViewById(R.id.expenseList);
        //int layout=android.R.layout.simple_list_item_1;

        String[] cols={"name","category","amount","date"};
        int[] views={R.id.tvExpenseName,R.id.tvAmount,R.id.tvCategory,R.id.tvDate};
        int layout=R.layout.segment_expense;
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,layout,cursor,cols,views);


        lv.setAdapter(adapter);
    }

}
