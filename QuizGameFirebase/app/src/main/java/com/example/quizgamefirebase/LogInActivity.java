package com.example.quizgamefirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonSignIn;
    private TextView textViewSignUp, textViewForgetPassword;
    private SignInButton signInButtonGoogle;
    private ProgressBar progressBarSignIn;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        signInButtonGoogle = (SignInButton) findViewById(R.id.signInButtonGoogle);
        textViewSignUp = findViewById(R.id.textViewSignUp);
        textViewForgetPassword = findViewById(R.id.textViewForgetPassword);
        progressBarSignIn = (ProgressBar) findViewById(R.id.progressBarSignIn);

        progressBarSignIn.setVisibility(View.INVISIBLE);

        buttonSignIn.setOnClickListener(view -> {

            String userEmail = editTextEmail.getText().toString();
            String userPassword = editTextPassword.getText().toString();

            signInFirebase(userEmail, userPassword);
            
        });

        signInButtonGoogle.setOnClickListener(view -> {

        });

        textViewSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        textViewForgetPassword.setOnClickListener(view -> {

        });
    }

    public void signInFirebase(String userEmail, String userPassword) {

        buttonSignIn.setClickable(false);
        progressBarSignIn.setVisibility(View.VISIBLE);

        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {

                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                progressBarSignIn.setVisibility(View.INVISIBLE);
                Toast.makeText(LogInActivity.this, "You've successfully logged in!", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(LogInActivity.this, "Something went wrong :( \n Please try again later", Toast.LENGTH_SHORT).show();

            }
        });

    }
}