package com.example.quizgamefirebase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewTime, textViewCorrectAnswers, textViewIncorrectAnswers,
            textViewQuestion, textViewAnswer1, textViewAnswer2, textViewAnswer3, textViewAnswer4;
    private Button buttonFinishGame, buttonNextQuestion;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Questions");

    private String quizQuestion, quizAnswer1, quizAnswer2, quizAnswer3, quizAnswer4, quizCorrectAnswer;
    private int questionCount;
    private int questionNumber = 0;

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

        callGameData();

        buttonNextQuestion.setOnClickListener(view -> {
            callGameData();
        });
    }

    public void callGameData() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                questionCount = (int) snapshot.getChildrenCount();

                quizQuestion = snapshot.child(String.valueOf(questionNumber)).child("q").getValue().toString();
                quizAnswer1 = snapshot.child(String.valueOf(questionNumber)).child("a").getValue().toString();
                quizAnswer2 = snapshot.child(String.valueOf(questionNumber)).child("b").getValue().toString();
                quizAnswer3 = snapshot.child(String.valueOf(questionNumber)).child("c").getValue().toString();
                quizAnswer4 = snapshot.child(String.valueOf(questionNumber)).child("d").getValue().toString();
                quizCorrectAnswer = snapshot.child(String.valueOf(questionNumber)).child("answer").getValue().toString();

                textViewQuestion.setText(quizQuestion);
                textViewAnswer1.setText(quizAnswer1);
                textViewAnswer2.setText(quizAnswer2);
                textViewAnswer3.setText(quizAnswer3);
                textViewAnswer4.setText(quizAnswer4);

                if (questionNumber < questionCount) {

                    questionNumber++;

                } else {

                    Toast.makeText(QuizActivity.this, "You answered all question", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(QuizActivity.this, "Sorry, an error has occurred", Toast.LENGTH_SHORT).show();

            }
        });
    }
}