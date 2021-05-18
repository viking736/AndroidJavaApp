package com.example.mapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText latitude = (EditText) findViewById(R.id.et);
        EditText longitude = (EditText) findViewById(R.id.et2);
        Button button = (Button) findViewById(R.id.btn);


        View.OnClickListener oclbtnMap = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("geo:" + latitude.getText().toString() + " " + longitude.getText().toString()));
                startActivity(intent1);
            }
        };
        
        button.setOnClickListener(oclbtnMap);
    }
}