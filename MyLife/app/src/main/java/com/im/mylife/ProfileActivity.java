package com.im.mylife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
    public void save(View view)
    {
        SharedPreferences profile=getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor profileEditor=profile.edit();
        EditText etName=findViewById(R.id.etName);
        EditText etEmail=findViewById(R.id.etEmail);
        EditText etCurrency=findViewById(R.id.etCurrency);
        profileEditor.putString("NAME",etName.getText().toString());
        profileEditor.putString("EMAIL",etEmail.getText().toString());
        profileEditor.putString("CURRENCY",etCurrency.getText().toString());
        profileEditor.commit();
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
