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

public class DailyExpenses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_expenses);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        String sql="SELECT * from expenses";
        DBHelper myDB=new DBHelper(this);
        SQLiteDatabase db=myDB.getReadableDatabase();

        Cursor cursor=db.rawQuery(sql,null);
        ListView lv=findViewById(R.id.expenseList);

        String[] cols={"reason","_id","amount"};
        int[] views={R.id.tvExpenseName,R.id.tvExpenseId,R.id.tvAmount};
        int layout=R.layout.segment_expense;

        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,layout,cursor,cols,views);

        lv.setAdapter(adapter);

    }
    public void complete(View view)
    {
        LinearLayout parent= (LinearLayout) view.getParent();
        TextView tvId=parent.findViewById(R.id.tvExpenseId);
        String id=tvId.getText().toString();
        String sql="DELETE FROM expenses WHERE _id="+id+"";
        DBHelper myDB=new DBHelper(this);
        SQLiteDatabase db=myDB.getWritableDatabase();
        db.execSQL(sql);
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        onResume();
    }
    public void open(View view)
    {
        TextView tvId=findViewById(R.id.tvExpenseId);
        String id=tvId.getText().toString();
        TextView tvName=findViewById(R.id.tvExpenseName);
        String name=tvName.getText().toString();
        TextView tvamount=findViewById(R.id.tvAmount);
        String amount=tvamount.getText().toString();
        Intent intent=new Intent(this, AddExpenses.class);
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        intent.putExtra("amount",amount);
        startActivity(intent);
    }

}
