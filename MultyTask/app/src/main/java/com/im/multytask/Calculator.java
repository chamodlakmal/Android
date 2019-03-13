package com.im.multytask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

public class Calculator extends AppCompatActivity {
    TextView error;
    TextView answer;
    EditText num1;
    EditText num2;
    String number1;
    String number2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        error=findViewById(R.id.textView4);
        answer=findViewById(R.id.textView9);
        num1=findViewById(R.id.editText);
        num2=findViewById(R.id.editText2);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
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
    public void sum(View view)
    {

        try {
            number1=num1.getText().toString();
            number2=num2.getText().toString();
            double firstNum=Double.parseDouble(number1);
            double secondNum=Double.parseDouble(number2);
            double sum=firstNum+secondNum;
            answer.setText("Answer = "+sum+"");
            error.setText("");
            }
            catch(NumberFormatException ex){
            error.setText("** Strings are not allowed. Please Enter Numbers.**");
                answer.setText("");
            }



    }
    public void sub(View view)
    {
        try {
            number1=num1.getText().toString();
            number2=num2.getText().toString();
            double firstNum=Double.parseDouble(number1);
            double secondNum=Double.parseDouble(number2);
            double sum=firstNum-secondNum;
            answer.setText("Answer = "+sum+"");
            error.setText("");
        }
        catch(NumberFormatException ex){
            error.setText("** Strings are not allowed. Please Enter Numbers.**");
            answer.setText("");
        }
    }
    public void mul(View view)
    {
        try {
            number1=num1.getText().toString();
            number2=num2.getText().toString();
            double firstNum=Double.parseDouble(number1);
            double secondNum=Double.parseDouble(number2);
            double sum=firstNum*secondNum;
            answer.setText("Answer = "+sum+"");
            error.setText("");
        }
        catch(NumberFormatException ex){
            error.setText("** Strings are not allowed. Please Enter Numbers.**");
            answer.setText("");
        }
    }
    public void dev(View view)
    {
        try {
            number1=num1.getText().toString();
            number2=num2.getText().toString();
            double firstNum=Double.parseDouble(number1);
            double secondNum=Double.parseDouble(number2);
            if(secondNum==0)
            {
                answer.setText("Answer = Undefined");
            }else
            {
                double sum=firstNum/secondNum;
                answer.setText("Answer = "+String.format("%.5g%n", sum));
            }

            error.setText("");
        }
        catch(NumberFormatException ex){
            error.setText("** Strings are not allowed. Please Enter Numbers.**");
            answer.setText("");
        }
    }
}
