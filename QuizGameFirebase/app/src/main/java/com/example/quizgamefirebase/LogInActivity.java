package com.example.quizgamefirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LogInActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonSignIn;
    private TextView textViewSignUp, textViewForgetPassword;
    private SignInButton signInButtonGoogle;
    private ProgressBar progressBarSignIn;

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private GoogleSignInClient client;

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

            if (!userEmail.isEmpty() || !userPassword.isEmpty()){

                signInFirebase(userEmail, userPassword);

            }else{

                Toast.makeText(LogInActivity.this, "Enter your email and password first",
                        Toast.LENGTH_SHORT).show();
                
            }


        });

        signInButtonGoogle.setOnClickListener(view -> {
            signInGoogle();
        });

        textViewSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        textViewForgetPassword.setOnClickListener(view -> {

            Intent intent = new Intent(LogInActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);

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

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {

            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }
    }

    public void signInGoogle() {

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        client = GoogleSignIn.getClient(this, options);

        signIn();
    }

    public void signIn() {
        Intent intentGoogle = client.getSignInIntent();
        startActivityForResult(intentGoogle, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            firebaseSignInWithGoogle(task);
        }
    }

    private void firebaseSignInWithGoogle(Task task) {
        try {
            GoogleSignInAccount account = (GoogleSignInAccount) task.getResult(ApiException.class);
            Toast.makeText(LogInActivity.this, "Signed in successfully!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            firebaseGoogleAccount(account);

        } catch (Throwable throwable) {
            Toast.makeText(LogInActivity.this, "Signing in failed", Toast.LENGTH_SHORT).show();
            throwable.printStackTrace();
        }
    }

    public void firebaseGoogleAccount(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser user = auth.getCurrentUser();

                }
            }
        });

    }
}