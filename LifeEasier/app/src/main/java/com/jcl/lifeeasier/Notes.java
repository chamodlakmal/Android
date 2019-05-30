package com.jcl.lifeeasier;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Notes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        String sql="SELECT * from notes WHERE status=0";
        DBHelper myDB=new DBHelper(this);
        SQLiteDatabase db=myDB.getReadableDatabase();

        Cursor cursor=db.rawQuery(sql,null);
        ListView lv=findViewById(R.id.noteList);

        String[] cols={"name","_id"};
        int[] views={R.id.tvNoteName,R.id.tvNoteId};
        int layout=R.layout.segment_note;

        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,layout,cursor,cols,views);

        lv.setAdapter(adapter);
    }
    public void complete(View view)
    {
        LinearLayout parent= (LinearLayout) view.getParent();
        TextView tvId=parent.findViewById(R.id.tvNoteId);
        String id=tvId.getText().toString();

        String sql="DELETE FROM notes WHERE _id="+id+"";
        DBHelper myDB=new DBHelper(this);
        SQLiteDatabase db=myDB.getWritableDatabase();
        db.execSQL(sql);
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        onResume();
    }
    public void open(View view)
    {
        TextView tvId=view.findViewById(R.id.tvNoteId);
        String id=tvId.getText().toString();
        TextView tvName=view.findViewById(R.id.tvNoteName);
        String name=tvName.getText().toString();

        Intent intent=new Intent(this, AddNote.class);
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        startActivity(intent);
    }

}
