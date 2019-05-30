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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TasksActivity.this,TaskAddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
       // List<String> list=new ArrayList<String>();
        String sql="SELECT id as _id,name,date,status from tasks WHERE status='0'";
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
        ListView lv=findViewById(R.id.taskList);
        //int layout=android.R.layout.simple_list_item_1;

        //ArrayAdapter adapter=new ArrayAdapter(this,layout,list);

        String[] cols={"name","date","_id"};
        int[] views={R.id.tvTaskName,R.id.tvTaskDate,R.id.tvTaskId};
        int layout=R.layout.segment_task;

        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,layout,cursor,cols,views);

        lv.setAdapter(adapter);
    }
    public void complete(View view)
    {
        LinearLayout parent= (LinearLayout) view.getParent();
        TextView tvId=parent.findViewById(R.id.tvTaskId);
        String id=tvId.getText().toString();
        String sql="UPDATE tasks SET status=1 WHERE id='"+id+"'";
        DBHelper myDB=new DBHelper(this);
        SQLiteDatabase db=myDB.getWritableDatabase();
        db.execSQL(sql);
        Toast.makeText(this, "Completed", Toast.LENGTH_SHORT).show();
        onResume();

    }
    public void open(View view)
    {
        TextView tvId=view.findViewById(R.id.tvTaskId);
        String id=tvId.getText().toString();
        Toast.makeText(this, "Id = "+id, Toast.LENGTH_SHORT).show();
        TextView tvName=view.findViewById(R.id.tvTaskName);
        String name=tvName.getText().toString();

        TextView tvDate=view.findViewById(R.id.tvTaskDate);
        String date=tvDate.getText().toString();

        Intent intent=new Intent(this,TaskAddActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        intent.putExtra("date",date);

        startActivity(intent);
    }
}
