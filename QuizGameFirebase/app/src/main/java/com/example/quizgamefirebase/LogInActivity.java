package com.example.quizgamefirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;

public class LogInActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonSignIn;
    private TextView textViewSignUp, textViewForgetPassword;
    private SignInButton signInButtonGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        signInButtonGoogle = (com.google.android.gms.common.SignInButton) findViewById(R.id.signInButtonGoogle);
        textViewSignUp = findViewById(R.id.textViewSignUp);
        textViewForgetPassword = findViewById(R.id.textViewForgetPassword);

        buttonSignIn.setOnClickListener(view->{

        });

        signInButtonGoogle.setOnClickListener(view->{

        });

        textViewSignUp.setOnClickListener(view->{
            Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        textViewForgetPassword.setOnClickListener(view->{

        });
    }
}