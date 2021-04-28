package com.example.javaapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
//        changeListener();
        onChangeListener();
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
    public void onChangeListener(){
        Button button = (Button) findViewById(R.id.setTextButton);
        EditText text = (EditText) findViewById(R.id.etTextRt);
        RatingBar rt = (RatingBar) findViewById(R.id.ratingBar);
        TextView ratText = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratText.setText(text.getText());
                rt.setRating(Float.parseFloat(ratText.getText().toString()));
            }
        });
    }
}