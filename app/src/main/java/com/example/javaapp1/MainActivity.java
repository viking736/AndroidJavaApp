package com.example.javaapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.javaapp1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View v){
        EditText num1 = (EditText) findViewById(R.id.et_num1);
        EditText num2 = (EditText) findViewById(R.id.et_num2);
        TextView result = (TextView) findViewById(R.id.tv_result);

        int number1 = Integer.parseInt(num1.getText().toString());
        int number2 = Integer.parseInt(num2.getText().toString());
        int resSum = number1 + number2;
        result.setText(Integer.toString(resSum));
    }
}