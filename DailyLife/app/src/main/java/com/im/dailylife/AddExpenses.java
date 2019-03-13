package com.im.dailylife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class AddExpenses extends AppCompatActivity {
    String file=null;
    String currency="LKR.";
    float total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
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
            EditText rtNote=findViewById(R.id.etReason);
            rtNote.setText(note);}
        else
        {

            Button btnDelete=findViewById(R.id.expenceDelete);
            btnDelete.setVisibility(View.INVISIBLE);


        }

        SharedPreferences profile=getSharedPreferences("profile", Context.MODE_PRIVATE);
        if(profile.contains("CURRENCY")) {
            TextView tvWelcome = findViewById(R.id.tvWelcomeMsg);
            currency= profile.getString("CURRENCY", "LKR");
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
    public void addExpense(View view)
    {
        String fileName=(file!=null)?file:String.valueOf(new Date().getTime());
        File file=new File(getFilesDir()+File.separator+"expenses"
                +File.separator+fileName);
        try {
            FileOutputStream fos=new FileOutputStream(file);
            EditText etReason=findViewById(R.id.etReason);
            String note=etReason.getText().toString()+" " ;
            EditText etTaskName=findViewById(R.id.etHowMuch);

            String FILENAME = "hello_file";
            try{
                FileInputStream fis =openFileInput(FILENAME);

                byte [] chars = new byte[fis.available()];
                fis.read(chars);
                fis.close();
                String string = new String(chars);
                total=Float.parseFloat(string);
            }catch (Exception e)
            {

            }



            String stotal=etTaskName.getText().toString();
            try{
                total+=Float.parseFloat(stotal);
                String totaltotal=String.valueOf(total);
                String howMuch=currency+"."+etTaskName.getText().toString()+" " ;
                fos.write(note.getBytes());
                fos.write(howMuch.getBytes());
                fos.close();
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,DailyExpenses.class);
                intent.putExtra ( "TextBox", etTaskName.getText().toString() );

                FileOutputStream fos1 =openFileOutput(FILENAME,MODE_PRIVATE);
                fos1.write(totaltotal.getBytes());
                fos1.close();

                startActivity(intent);

            }
            catch(Exception e)
            {
                etReason.setText("Strings are not allowed . please enter numbers");
            }


        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error not Saved", Toast.LENGTH_SHORT).show();
        }
    }
    public void expenseDelete(View view)
    {
        File filetoDelete=new File(getFilesDir()+File.separator+"expenses");

        deleteRecursive(filetoDelete);
        File filetoDelete1=new File(getFilesDir()+File.separator+"hello_file");
        deleteRecursive(filetoDelete1);

        Toast.makeText(this, "Deleted All", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,DailyExpenses.class);
        startActivity(intent);
    }
    public void deleteRecursive(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }

        fileOrDirectory.delete();
    }
}
