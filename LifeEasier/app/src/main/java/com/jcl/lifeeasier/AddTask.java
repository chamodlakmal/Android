package com.jcl.lifeeasier;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {
    String file=null;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Intent intent=getIntent();
        Bundle bundle= intent.getExtras();
        if(bundle!=null) {
            String note = bundle.getString("name");
            id=bundle.getString("id");

            EditText etTask=findViewById(R.id.etTaskName);
            TextView id1=findViewById(R.id.tvTaskId);
            etTask.setText(note);
        }
        else
        {

            id=null;


        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void saveTask(View view)
    {
        DBHelper myDB=new DBHelper(this);
        SQLiteDatabase db=myDB.getWritableDatabase();
        EditText etTaskName=findViewById(R.id.etTaskName);
        String note=etTaskName.getText().toString() ;
        TimePicker fromTime=findViewById(R.id.tptaskfrom);
        TimePicker toTime=findViewById(R.id.tptaskto);
        int fromhour = fromTime.getCurrentHour();
        int fromminute = fromTime.getCurrentMinute();
        int tohour = toTime.getCurrentHour();
        int tominute = toTime.getCurrentMinute();
        String sfromHour=Integer.toString(fromhour);
        String sfromminute = Integer.toString(fromminute);
        String stohour = Integer.toString(tohour);
        String stominute = Integer.toString(tominute);
        String frommTime="fromTime :"+sfromHour+":"+sfromminute+" ";
        String tomTime="toTime :"+ stohour+":"+stominute+" ";
        String sql="";


        if(id==null)
        {
            sql="INSERT into tasks (name,fromtime,totime,status)values('"+note+"','"+frommTime+"','"+tomTime+"',0)";
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

        }
        else
        {
            sql="UPDATE tasks SET name='"+note+"',fromtime='"+frommTime+"',totime='"+tomTime+"',status=0 WHERE _id='"+id+"'";
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        }

        db.execSQL(sql);
        Intent intent=new Intent(this,DailyTasks.class);
        startActivity(intent);

    }

}
