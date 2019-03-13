package com.im.funapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class BroadcastReciever extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_reciever);
    }
    public void register(View view)
    {
        IntentFilter filter=new IntentFilter();
        filter.addAction("android.intent.action.PHONE_STATE");
        MyReciever reciever=new MyReciever();
        registerReceiver(reciever,filter);
        Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show();

    }
    public void UnRegister(View v)
    {
        MyReciever reciever=new MyReciever();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(reciever);
        Toast.makeText(this, "Un Registered", Toast.LENGTH_SHORT).show();
    }
}
