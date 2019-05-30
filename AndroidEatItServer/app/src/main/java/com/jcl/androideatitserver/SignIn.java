package com.jcl.androideatitserver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jcl.androideatitserver.Common.Common;
import com.jcl.androideatitserver.Model.User;

import java.util.prefs.Preferences;

public class SignIn extends AppCompatActivity {

    EditText phone,password;
    Button btnSignIn;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        phone=findViewById(R.id.edtPhone);
        password=findViewById(R.id.edtPassword);
        btnSignIn=findViewById(R.id.signIn);

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("User");
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser(phone.getText().toString(),password.getText().toString());
            }
        });

    }

    private void signInUser(String phone, String password) {
        final ProgressDialog dialog=new ProgressDialog(SignIn.this);
        dialog.setMessage("Please Waiting ...");
        dialog.show();
        final  String phone1=phone;
        final String password1=password;
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(phone1).exists())
                {
                    dialog.dismiss();
                    User user=dataSnapshot.child(phone1).getValue(User.class);
                    user.setPhone(phone1);
                    if(Boolean.parseBoolean(user.getIsStaff()))
                    {
                        if(user.getPassword().equals(password1))
                        {
                            Common.currentUser=user;
                            Intent intent=new Intent(SignIn.this,Home.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(SignIn.this, "Password is Wrong !", Toast.LENGTH_SHORT).show();
                        }
                    }else
                    {
                        Toast.makeText(SignIn.this, "Please Login with staff Account", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    dialog.dismiss();
                    Toast.makeText(SignIn.this, "User not Exist", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
