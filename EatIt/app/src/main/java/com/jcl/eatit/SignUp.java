package com.jcl.eatit;

import android.app.ProgressDialog;
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
import com.jcl.eatit.Model.User;

public class SignUp extends AppCompatActivity {
    EditText name,password,phone;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=findViewById(R.id.edtName);
        password=findViewById(R.id.edtPassword);
        phone=findViewById(R.id.edtPhone);
        signUp=findViewById(R.id.signUp);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("User");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog=new ProgressDialog(SignUp.this);
                progressDialog.setMessage("Please Wait ...");
                progressDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone.getText().toString()).exists())
                        {
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this, "Phone Number is Exist !", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            progressDialog.dismiss();
                            User user=new User(name.getText().toString(),password.getText().toString());
                            table_user.child(phone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Sign Up Successfully !!!", Toast.LENGTH_SHORT).show();
                            fileList();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
