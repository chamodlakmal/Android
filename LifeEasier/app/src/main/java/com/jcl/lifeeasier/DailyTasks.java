package com.jcl.lifeeasier;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DailyTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_tasks);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DailyTasks.this,AddTask.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        String sql="SELECT * from tasks";
        DBHelper myDB=new DBHelper(this);
        SQLiteDatabase db=myDB.getReadableDatabase();

        Cursor cursor=db.rawQuery(sql,null);
        ListView lv=findViewById(R.id.taskList);

        String[] cols={"name","_id","fromtime","totime"};
        int[] views={R.id.tvTaskName,R.id.tvTaskId,R.id.tvfromtime,R.id.tvtotime};
        int layout=R.layout.segment_task;

        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,layout,cursor,cols,views);

        lv.setAdapter(adapter);


    }
    public void complete(View view)
    {
        LinearLayout parent= (LinearLayout) view.getParent();
        TextView tvId=parent.findViewById(R.id.tvTaskId);
        String id=tvId.getText().toString();
        String sql="DELETE FROM tasks WHERE _id="+id+"";
        DBHelper myDB=new DBHelper(this);
        SQLiteDatabase db=myDB.getWritableDatabase();
        db.execSQL(sql);
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        onResume();
    }
    public void open(View view)
    {
        TextView tvId=findViewById(R.id.tvTaskId);
        String id=tvId.getText().toString();
        TextView tvName=findViewById(R.id.tvTaskName);
        String name=tvName.getText().toString();
        TextView fromTime=findViewById(R.id.tvfromtime);
        String amount=fromTime.getText().toString();
        TextView totime=findViewById(R.id.tvtotime);
        String amount1=totime.getText().toString();
        Intent intent=new Intent(this, AddTask.class);
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        intent.putExtra("fromTime", amount);
        intent.putExtra("toTime",amount1);
        startActivity(intent);
    }


}
