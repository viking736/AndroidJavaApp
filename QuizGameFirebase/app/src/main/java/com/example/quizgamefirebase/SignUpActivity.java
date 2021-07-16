package com.example.quizgamefirebase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextUserEmail, editTextUserPassword;
    private Button buttonUserSignUp;
    private ProgressBar progressBar;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextUserEmail = (EditText) findViewById(R.id.editTextUserEmail);
        editTextUserPassword = (EditText) findViewById(R.id.editTextUserPassword);
        buttonUserSignUp = (Button) findViewById(R.id.buttonUserSignUp);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        buttonUserSignUp.setOnClickListener(view -> {

            buttonUserSignUp.setClickable(false);

            String userEmail = editTextUserEmail.getText().toString();
            String userPassword = editTextUserPassword.getText().toString();

            signUpFirebase(userEmail, userPassword);
        });
    }

    public void signUpFirebase(String userEmail, String userPassword) {

        progressBar.setVisibility(View.VISIBLE);

        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(SignUpActivity.this, "Your account is created!", Toast.LENGTH_SHORT).show();
                finish();
                progressBar.setVisibility(View.INVISIBLE);
            } else {
                Toast.makeText(SignUpActivity.this, "An error has occurred :( \n Please try later", Toast.LENGTH_SHORT).show();
            }
        });
    }
}