package com.example.swipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements GestureDetector.OnGestureListener {

    public float x1, x2, y1, y2;
    public int distance = 100;
    public int distance2 = 250;
    public GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.gestureDetector = new GestureDetector(MainActivity.this, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

            float valueX = x2 - x1;
            float valueY = y2 - y1;
            if (Math.abs(valueX) > distance){
                if (x2 > x1 && Math.abs(valueY)< distance2){
                    Toast.makeText(MainActivity.this, "Motion right", Toast.LENGTH_SHORT).show();
                }
                if (x2 < x1 && Math.abs(valueY) < distance2){
                    Toast.makeText(MainActivity.this, "Motion left", Toast.LENGTH_SHORT).show();
                }
            }
            if (Math.abs(valueY) > distance){
                if (y2 > y1 && Math.abs(valueX)< distance2){
                    Toast.makeText(MainActivity.this, "Motion down", Toast.LENGTH_SHORT).show();
                }
                if (y2 < y1 && Math.abs(valueX) < distance2){
                    Toast.makeText(MainActivity.this, "Motion up", Toast.LENGTH_SHORT).show();
                }
            }
        }


        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}