package com.im.dailylife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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

    public void save(View view)
    {
        SharedPreferences profile=getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor profileEditor=profile.edit();
        EditText etName=findViewById(R.id.etName);
        EditText etEmail=findViewById(R.id.etEmail);
        EditText etCurrency=findViewById(R.id.etCurrency);
        EditText phone=findViewById(R.id.etNumber);
        profileEditor.putString("NAME",etName.getText().toString());
        profileEditor.putString("EMAIL",etEmail.getText().toString());
        profileEditor.putString("CURRENCY",etCurrency.getText().toString());
        profileEditor.putString("PHONE",phone.getText().toString());
        profileEditor.commit();
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
