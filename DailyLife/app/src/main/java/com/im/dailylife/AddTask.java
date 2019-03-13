package com.im.dailylife;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import static com.im.dailylife.R.id.taskName;

public class AddTask extends AppCompatActivity {
    String file=null;

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
            String note = bundle.getString("note");
            file = bundle.getString("file");
            EditText rtNote=findViewById(R.id.etTaskName);
            rtNote.setText(note);}
            else
        {

                Button btnDelete=findViewById(R.id.taskDelete);
                btnDelete.setVisibility(View.INVISIBLE);


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
        String fileName=(file!=null)?file:String.valueOf(new Date().getTime());
        File file=new File(getFilesDir()+File.separator+"tasks"
                +File.separator+fileName);
        try {
            FileOutputStream fos=new FileOutputStream(file);
            EditText etTaskName=findViewById(R.id.etTaskName);
            String note=etTaskName.getText().toString()+" " ;
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
            String frommTime=" From :- "+sfromHour+" : "+sfromminute+" ";
            String tomTime=" to :- "+stohour+" : "+stominute+" ";
            fos.write(note.getBytes());
            fos.write(frommTime.getBytes());
            fos.write(tomTime.getBytes());
            fos.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,DailyTasks.class);
            startActivity(intent);



        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error not Saved", Toast.LENGTH_SHORT).show();
        }
    }
    public void deleteTask(View view)
    {
        File filetoDelete=new File(getFilesDir()+File.separator+"tasks"+File.separator+file);
        filetoDelete.delete();
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,DailyTasks.class);
        startActivity(intent);
    }
}
