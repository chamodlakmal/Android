package com.im.vinora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
    public void openProfile(View view)
    {
        Intent intent=new Intent(this,Profile.class);
        startActivity(intent);
    }
    public void openOrdringItem(View view)
    {
        Intent intent=new Intent(this,OrderingItems.class);
        startActivity(intent);
    }
    public void openOrder(View view)
    {
        Intent intent=new Intent(this,MyOrders.class);
        startActivity(intent);
    }
}
