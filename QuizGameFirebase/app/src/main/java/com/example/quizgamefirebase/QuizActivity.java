package com.example.quizgamefirebase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewTime, textViewCorrectAnswers, textViewIncorrectAnswers,
            textViewQuestion, textViewAnswer1, textViewAnswer2, textViewAnswer3, textViewAnswer4;
    private Button buttonFinishGame, buttonNextQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewTime = findViewById(R.id.textViewTime);
        textViewCorrectAnswers = findViewById(R.id.textViewCorrectAnswers);
        textViewIncorrectAnswers = findViewById(R.id.textViewIncorrectAnswers);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewAnswer1 = findViewById(R.id.textViewAnswer1);
        textViewAnswer2 = findViewById(R.id.textViewAnswer2);
        textViewAnswer3 = findViewById(R.id.textViewAnswer3);
        textViewAnswer4 = findViewById(R.id.textViewAnswer4);
        buttonFinishGame = (Button) findViewById(R.id.buttonFinish);
        buttonNextQuestion = (Button) findViewById(R.id.buttonNextQuestion);
    }
}