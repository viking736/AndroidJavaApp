package com.example.javaapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity8 extends AppCompatActivity {

    private EditText someText;
    private TextView tvTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        someText = (EditText) findViewById(R.id.edit);
        tvTxt = (TextView) findViewById(R.id.textView4);
    }

    public void read(View view){
        try {
            FileInputStream fileInput = openFileInput("Example.txt");
            InputStreamReader reader = new InputStreamReader(fileInput);
            BufferedReader buffer = new BufferedReader(reader);
            StringBuffer stringBuffer = new StringBuffer();
            String lines;
            while ((lines = buffer.readLine()) != null){
                stringBuffer.append(lines + "\n");
            }
            tvTxt.setText(stringBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void write(View view){
        String mytxt = someText.getText().toString();
        try {
            FileOutputStream fileOutput = openFileOutput("Example.txt", MODE_PRIVATE);
            fileOutput.write(mytxt.getBytes());
            fileOutput.close();
            Toast.makeText(MainActivity8.this, "Save successfull", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}