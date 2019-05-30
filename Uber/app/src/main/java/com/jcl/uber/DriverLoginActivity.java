package com.jcl.uber;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverLoginActivity extends AppCompatActivity {
    private EditText email,password;
    private Button login,registration;
    private FirebaseAuth mAuth;
    private  FirebaseAuth.AuthStateListener firebaseAuthListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);
        email=findViewById(R.id.etEmail);
        password=findViewById(R.id.etPassword);
        login=findViewById(R.id.driverLogin);
        registration=findViewById(R.id.driverRegistration);
        mAuth=FirebaseAuth.getInstance();
        firebaseAuthListner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null)
                {
                    Intent intent=new Intent(DriverLoginActivity.this,MapActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email1=email.getText().toString();
                final String password1=password.getText().toString();
                mAuth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(DriverLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(DriverLoginActivity.this, "Sign Up error", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String userId=mAuth.getCurrentUser().getUid();
                            DatabaseReference currentUserId= FirebaseDatabase.getInstance().getReference().child("Users").child("Riders").child(userId);
                            currentUserId.setValue(true);
                        }
                    }
                });
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email1=email.getText().toString();
                final String password1=password.getText().toString();
                mAuth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(DriverLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(DriverLoginActivity.this, "Sign In error", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListner);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListner);
    }
}
