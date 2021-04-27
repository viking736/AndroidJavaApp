package com.example.javaapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        changeListener();
    }

    public void changeListener(){
        RatingBar rating = (RatingBar) findViewById(R.id.ratingBar);
        TextView someText = (TextView) findViewById(R.id.textView);

        rating.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        someText.setText(String.valueOf(rating));
            }
        }
        );
    }
}