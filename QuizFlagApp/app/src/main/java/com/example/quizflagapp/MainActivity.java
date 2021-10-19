package com.example.quizflagapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private Button buttonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextName = (EditText) findViewById(R.id.et_name);
        buttonName = (Button) findViewById(R.id.btn_begin_quiz);

        buttonName.setOnClickListener(view -> {

            String name = editTextName.getText().toString();

            if (!name.isEmpty()){

                Intent intent = new Intent(MainActivity.this, QuizQuestionsActivity.class);
                intent.putExtra(Question.USER_NAME, name);
                startActivity(intent);
                finish();

            }else{

                Toast.makeText(MainActivity.this, "Please, enter your name \nbefore pressing the button :)", Toast.LENGTH_SHORT).show();

            }


        });


    }
}