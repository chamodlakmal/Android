package com.im.mylife;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TaskAddActivity extends AppCompatActivity {
    String file=null;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);
        Intent intent=getIntent();
        Bundle bundle= intent.getExtras();
        if(bundle!=null) {
            String name = bundle.getString("name");
            String date = bundle.getString("date");
            id = bundle.getString("id");
            file = bundle.getString("file");
            EditText rtNote=findViewById(R.id.etTask);
            EditText date1=findViewById(R.id.etDate);
            rtNote.setText(name);
            date1.setText((CharSequence) date);
        }
        else
        {
            id=null;
        }
    }
    public void tasksave(View view)
    {
        DBHelper myDB=new DBHelper(this);
        SQLiteDatabase db=myDB.getWritableDatabase();
        EditText etName=findViewById(R.id.etTask);
        EditText etDate=findViewById(R.id.etDate);
        String sql="";
        String taskName=etName.getText().toString();
        String taskDate=etDate.getText().toString();
        if(id==null)
        {
            sql="INSERT into tasks (name,date,status) values('"+etName.getText().toString()+"','"+etDate.getText().toString()+"','0')";

        }
        else
        {
            sql="UPDATE tasks SET name='"+taskName+"',date='"+taskDate+"' WHERE _id='"+id+"'";
        }

        db.execSQL(sql);
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,TasksActivity.class);
        startActivity(intent);
    }
}
