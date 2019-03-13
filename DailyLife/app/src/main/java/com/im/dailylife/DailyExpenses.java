package com.im.dailylife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyExpenses extends AppCompatActivity {
    Map<Integer,String> data=new HashMap<Integer, String>();
    String currency="LKR ";
    static String text="0";
    float total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_expenses);

        Intent i = getIntent();
        Bundle bundle= i.getExtras();


            TextView txtExpence = findViewById(R.id.txtExpense);

            try {

                String FILENAME = "hello_file";
                FileInputStream fis =openFileInput(FILENAME);

                byte [] chars = new byte[fis.available()];
                fis.read(chars);
                fis.close();
                String string = new String(chars);
                total=Float.parseFloat(string);


                txtExpence.setText("Your total Expence = " + total);
            }catch (Exception e){
                txtExpence.setText("Your total Expence = " + total);
            }



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
                Intent intent =new Intent(DailyExpenses.this,AddExpenses.class);
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
        File folder=new File(getFilesDir()+File.separator+"expenses");
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
        ListView lv=findViewById(R.id.expenseList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView) view;
                String note=tv.getText().toString();
                String file=data.get(position);
                Intent intent=new Intent(DailyExpenses.this,AddExpenses.class);
                intent.putExtra("note",note);
                intent.putExtra("file",file);

                startActivity(intent);
            }
        });

    }


}
