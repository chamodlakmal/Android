package com.im.multytask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void openStopWatch(View view)
    {
        Intent intent=new Intent(getApplicationContext(),StopWatch.class);
        //getApplicationContext()=this,MainActivity.this
        startActivity(intent);

    }
    public void openSong(View view)
    {
        Intent intent=new Intent(getApplicationContext(),ServiceActivity.class);
        startActivity(intent);

    }
    public void openPhoto(View view)
    {
        Intent intent=new Intent(getApplicationContext(),Photo.class);
        //getApplicationContext()=this,MainActivity.this
        startActivity(intent);

    }

    public void openCalculator(View view)
    {
        Intent intent=new Intent(getApplicationContext(),Calculator.class);
        //getApplicationContext()=this,MainActivity.this
        startActivity(intent);
    }

}
