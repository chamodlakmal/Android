package com.jcl.lifeeasier;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddExpenses extends AppCompatActivity {
    String file=null;
    String currency="LKR.";
    float total=0;
    String id;
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
            String note = bundle.getString("name");
            id=bundle.getString("id");
            String amount=bundle.getString("amount");
            EditText etExpense=findViewById(R.id.etReason);
            EditText etReason=findViewById(R.id.etHowMuch);
            etExpense.setText(note);
            etReason.setText(amount);

        }
        else
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
    public void addExpense(View view)
    {
        DBHelper myDB=new DBHelper(this);
        SQLiteDatabase db=myDB.getWritableDatabase();
        EditText etReason=findViewById(R.id.etReason);
        EditText howMuch=findViewById(R.id.etHowMuch);

        String sql="";
        String reason=etReason.getText().toString();
        String how=howMuch.getText().toString();
        Double dHow=Double.parseDouble(how);
        if(id==null)
        {
            sql="INSERT into expenses (reason,amount)values('"+reason+"',"+dHow+")";
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

        }
        else
        {
            sql="UPDATE expenses SET reason='"+reason+"',amount="+dHow+" WHERE _id='"+id+"'";
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        }

        db.execSQL(sql);
        Intent intent=new Intent(this,DailyExpenses.class);
        startActivity(intent);

    }
}
