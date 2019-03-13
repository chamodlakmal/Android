package com.im.multytask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
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
    public void start(View view)
    {
        Intent intent=new Intent(this,MySong.class);
        startService(intent);
        Toast.makeText(this, "Playing the Song ....", Toast.LENGTH_SHORT).show();
    }
    public void stop(View view)
    {
        Intent intent=new Intent(this,MySong.class);
        stopService(intent);
        Toast.makeText(this, "Stop the Song", Toast.LENGTH_SHORT).show();
    }
}
