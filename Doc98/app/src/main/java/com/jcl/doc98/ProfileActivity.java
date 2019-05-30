package com.jcl.doc98;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences profile=getSharedPreferences("profile", Context.MODE_PRIVATE);
        if(profile.contains("NAME")) {
            TextView name = findViewById(R.id.etName);
            TextView email = findViewById(R.id.etEmail);
            TextView tel = findViewById(R.id.etNumber);
            String name1 = profile.getString("NAME", "Guest");
            String email1 = profile.getString("EMAIL", "Guest");
            String tel1 = profile.getString("PHONE", "Guest");
            name.setText(name1);
            email.setText(email1);
            tel.setText(tel1);
        }else
        {
            Intent intent=new Intent(this,ProfileActivity.class);
            startActivity(intent);
        }
    }

    public void save(View view)
    {
        SharedPreferences profile=getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor profileEditor=profile.edit();
        EditText etName=findViewById(R.id.etName);
        EditText etEmail=findViewById(R.id.etEmail);

        EditText phone=findViewById(R.id.etNumber);
        profileEditor.putString("NAME",etName.getText().toString());
        profileEditor.putString("EMAIL",etEmail.getText().toString());

        profileEditor.putString("PHONE",phone.getText().toString());
        profileEditor.commit();
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
