package com.example.flappybird;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    TextView gameScore;
    TextView gameBestScore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        int score = getIntent().getExtras().getInt("Score");
        SharedPreferences pref = getSharedPreferences("myPref", 0);
        int bestScore = pref.getInt("bestScore", 0);
        SharedPreferences.Editor editor= pref.edit();

        if (score > bestScore){
            bestScore = score;
            editor.putInt("bestScore", bestScore);
            editor.commit();
        }

        gameScore = (TextView) findViewById(R.id.game_score);
        gameBestScore = (TextView) findViewById(R.id.game_best_score);
        gameScore.setText(String.valueOf(score));
        gameBestScore.setText(String.valueOf(bestScore));


    }
    public void restart(View view){
        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void exit(){
        finish();
    }
}
