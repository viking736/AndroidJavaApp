package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static CountDownTimer countDownTimer;
    private static long timerDuration = 60000;
    private static long pauseOffset = 0;
    private TextView tvTimer;
    private Button btnStart;
    private Button btnPause;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTimer = (TextView) findViewById(R.id.timer);
        tvTimer.setText(String.valueOf(timerDuration/1000));
        btnStart = (Button) findViewById(R.id.btn_start);
        btnPause = (Button) findViewById(R.id.btn_pause);
        btnReset = (Button) findViewById(R.id.btn_reset);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer(pauseOffset);
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });


    }
    public void startTimer(long pauseOffset1){
        countDownTimer = new CountDownTimer(timerDuration - pauseOffset1, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                pauseOffset = timerDuration - millisUntilFinished;
                tvTimer.setText(String.valueOf(millisUntilFinished/1000));
            }
            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "The timer is finished", Toast.LENGTH_LONG).show();
            }
        }.start();
    }

    public void pauseTimer(){
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }
    public void resetTimer(){
        if (countDownTimer != null){
            countDownTimer.cancel();
            tvTimer.setText(String.valueOf(timerDuration/1000));
            countDownTimer = null;
            pauseOffset = 0;
        }
    }
}