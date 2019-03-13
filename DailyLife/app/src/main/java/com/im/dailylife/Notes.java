package com.im.dailylife;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notes extends AppCompatActivity {
    Map<Integer,String> data=new HashMap<Integer, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Notes.this,AddNote.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        File folder=new File(getFilesDir()+File.separator+"notes");
        if(!folder.exists())
        {
            folder.mkdir();
        }
        List<String> list=new ArrayList<String>();

        File[] files=folder.listFiles();
        int index=0;
        data.clear();
        for(File file:files)
        {
            data.put(index,file.getName());
            index++;
            try {
                FileInputStream fis=new FileInputStream(file);
                byte[] chars=new byte[fis.available()];
                fis.read(chars);
                fis.close();
                String note=new String(chars);
                list.add(note);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        int layout=android.R.layout.simple_list_item_1;
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,layout,list);
        ListView lv=findViewById(R.id.noteList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView) view;
                String note=tv.getText().toString();
                String file=data.get(position);
                Intent intent=new Intent(Notes.this,AddNote.class);
                intent.putExtra("note",note);
                intent.putExtra("file",file);
                startActivity(intent);
            }
        });

    }

}
