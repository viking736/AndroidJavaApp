package com.example.quizgamefirebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart, buttonSignOut;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonStart);
        buttonSignOut = findViewById(R.id.buttonSignOut);

        buttonStart.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        });

        buttonSignOut.setOnClickListener(view -> {

            auth.signOut();

            Intent intent = new Intent(MainActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();

        });
    }
}