package com.jcl.lifeeasier;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNote extends AppCompatActivity {
    String file=null;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Intent intent=getIntent();
        Bundle bundle= intent.getExtras();
        if(bundle!=null) {
            id=bundle.getString("id");
            String note = bundle.getString("name");
            EditText rtNote=findViewById(R.id.etNote);
            rtNote.setText(note);
        }else
        {
            id=null;

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
    public  void notesave(View view)
    {
        DBHelper myDB=new DBHelper(this);
        SQLiteDatabase db=myDB.getWritableDatabase();
        EditText etName=findViewById(R.id.etNote);

        String sql="";
        String noteName=etName.getText().toString();

        if(id==null)
        {
            sql="INSERT into notes (name,status)values('"+noteName+"',0)";

        }
        else
        {
            sql="UPDATE notes SET name='"+noteName+"' WHERE _id='"+id+"'";
        }

        db.execSQL(sql);
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,Notes.class);
        startActivity(intent);

    }

}
