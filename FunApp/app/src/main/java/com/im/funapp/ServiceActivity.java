package com.im.funapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }
    public void start(View view)
    {
        Intent intent=new Intent(this,MyService.class);
        startService(intent);
    }
    public void stop(View view)
    {
        Intent intent=new Intent(this,MyService.class);
        stopService(intent);
    }
}
