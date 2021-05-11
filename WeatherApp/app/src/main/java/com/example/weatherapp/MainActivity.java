package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText user_field;
    private Button main_button;
    private TextView result_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_field = findViewById(R.id.user_field);
        main_button = findViewById(R.id.main_button);
        result_info = findViewById(R.id.result_info);

        main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_field.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainActivity.this, R.string.warning, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}