package com.example.quizgamefirebase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageViewSplash;
    private TextView textViewSplash;

    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageViewSplash = (ImageView) findViewById(R.id.imageViewSplash);
        textViewSplash = findViewById(R.id.textViewSplash);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_anim);

        imageViewSplash.setAnimation(animation);
        textViewSplash.setAnimation(animation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LogInActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}