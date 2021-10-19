package com.example.quizflagapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewUserName, textViewUserScore;
    private Button buttonFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewUserName = (TextView) findViewById(R.id.tv_user_name);
        textViewUserScore = (TextView) findViewById(R.id.tv_user_score);

        buttonFinish = (Button) findViewById(R.id.btn_finish);

        String userName = getIntent().getStringExtra(Question.USER_NAME);
        int userScore = getIntent().getIntExtra(Question.CORRECT_ANSWERS,0);
        int totalQuestions = getIntent().getIntExtra(Question.TOTAL_QUESTIONS, 0);

        textViewUserName.setText(userName);
        textViewUserScore.setText("Your score is " + userScore+ " out of " + totalQuestions);

        buttonFinish.setOnClickListener(view->{

            Intent intent = new Intent(ResultActivity.this, MainActivity.class);

            startActivity(intent);

            finish();

        });

    }
}