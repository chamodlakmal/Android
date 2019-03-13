package com.im.multytask;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StopWatch extends AppCompatActivity {
    Button buStart,buPause,buReset;
    TextView txtView;
    Handler customHandler=new Handler();
    ConstraintLayout layout;
    Long startTime=0L,timeMilisec=0L,timeSwap=0L,updateTime=0L;

    Runnable updateTimerThread=new Runnable() {
        @Override
        public void run() {
           timeMilisec=SystemClock.uptimeMillis()-startTime;
           updateTime=timeSwap+timeMilisec;
           int secs=(int)(updateTime/1000);
           int mins=secs/60;
           secs%=60;
           int milisec=(int)(updateTime%1000);
           txtView.setText(""+mins+":"+String.format("%2d",secs)+":"+String.format("%2d",milisec));
           customHandler.postDelayed(this,0);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        buStart=findViewById(R.id.buStart);
        buPause=findViewById(R.id.buPause);
        buReset=findViewById(R.id.buReset);
        txtView=findViewById(R.id.txtStop);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        buStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime= SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread,0);
            }
        });

        buPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSwap+=timeMilisec;
                customHandler.removeCallbacks(updateTimerThread);
            }
        });
        buReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeMilisec=0L;timeSwap=0L;updateTime=0L;
                txtView.setText("0:00:000");
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
}
