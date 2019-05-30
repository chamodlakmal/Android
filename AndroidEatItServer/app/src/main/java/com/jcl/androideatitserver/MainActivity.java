package com.jcl.androideatitserver;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button btnSignIn;
    TextView txtView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn=findViewById(R.id.signIn);
        txtView=findViewById(R.id.txtSlogan);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/NABILA.TTF");
        txtView.setTypeface(typeface);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignIn.class);
                startActivity(intent);
            }
        });
    }
}
