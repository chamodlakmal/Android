package com.jcl.doc98;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);

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
    public void openProfile(View view)
    {
        Intent intent=new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
}
