package com.example.javaapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        login();
    }

    public String trueEmail = "example@gmail.com";
    public String truePassword = "12345";

    public void login(){
        ImageView img = (ImageView) findViewById(R.id.imageView3);
        Button btn = (Button) findViewById(R.id.button);
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trueEmail.equals(String.valueOf(email.getText())) && truePassword.equals(String.valueOf(password.getText()))){
                    img.setImageResource(R.drawable.correct);
                }else{
                    img.setImageResource(R.drawable.incorrect);
                }
            }
        });
    }

}