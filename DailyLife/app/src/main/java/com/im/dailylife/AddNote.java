package com.im.dailylife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class AddNote extends AppCompatActivity {
    String file=null;
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
            String note = bundle.getString("note");
            file = bundle.getString("file");
            EditText rtNote=findViewById(R.id.etNote);
            rtNote.setText(note);
        }else
        {
            Button btnDelete=findViewById(R.id.note_delete);
            btnDelete.setVisibility(View.INVISIBLE);

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
        String fileName=(file!=null)?file:String.valueOf(new Date().getTime());
        File file=new File(getFilesDir()+File.separator+"notes"
                +File.separator+fileName);
        try {
            FileOutputStream fos=new FileOutputStream(file);
            EditText etNote=findViewById(R.id.etNote);
            String note=etNote.getText().toString();
            fos.write(note.getBytes());
            fos.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,Notes.class);
            startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error not Saved", Toast.LENGTH_SHORT).show();
        }

    }
    public void notedelete(View view)
    {
        File filetoDelete=new File(getFilesDir()+File.separator+"notes"+File.separator+file);
        filetoDelete.delete();
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,Notes.class);
        startActivity(intent);
    }
}
