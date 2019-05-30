package com.im.mylife;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ExpenceAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expence_add);
    }
    public void expenseSave(View view)
    {
        DBHelper myDB=new DBHelper(this);
        SQLiteDatabase db=myDB.getWritableDatabase();
        EditText exName=findViewById(R.id.exName);
        EditText exDate=findViewById(R.id.exDate);
        EditText category=findViewById(R.id.exCategory);
        EditText amount=findViewById(R.id.exAmount);
        String sql="INSERT into expense (name,category,amount,date) values('"+exName.getText().toString()+"','"+category.getText().toString()+"','"+amount.getText().toString()+"','"+exDate.getText().toString()+")";
        db.execSQL(sql);
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,ExpencesActivity.class);
        startActivity(intent);
    }
}
