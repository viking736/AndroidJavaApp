package com.example.quizgamefirebase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText editTextForgotPasswordEmail;
    private Button buttonContinue;
    private ProgressBar progressBarPassword;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editTextForgotPasswordEmail = (EditText) findViewById(R.id.editTextForgotPasswordEmail);
        buttonContinue = (Button) findViewById(R.id.buttonContinue);
        progressBarPassword = (ProgressBar) findViewById(R.id.progressBarPassword);

        progressBarPassword.setVisibility(View.INVISIBLE);

        buttonContinue.setOnClickListener(view -> {

            String userEmail = editTextForgotPasswordEmail.getText().toString();
            resetPassword(userEmail);
        });
    }

    public void resetPassword(String userEmail) {

        progressBarPassword.setVisibility(View.VISIBLE);

        auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(ForgotPasswordActivity.this, "We sent an email to reset your password", Toast.LENGTH_SHORT).show();
                    buttonContinue.setClickable(false);
                    progressBarPassword.setVisibility(View.INVISIBLE);
                    finish();

                } else {

                    Toast.makeText(ForgotPasswordActivity.this, "Sorry, an error has occurred, \n Try later to reset yor password",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}