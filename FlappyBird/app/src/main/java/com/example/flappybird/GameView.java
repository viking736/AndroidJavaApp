package com.example.flappybird;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;

import android.os.Handler;

public class GameView extends View {

    Handler handler;
    Runnable runnable;
    int UPDATE_SECONDS = 60;
    Bitmap background;
    Display display;
    Point point;
    int dWidth, dHeight;
    Rect rect;
    Bitmap[] birds;
    int birdFrame = 0;
    int birdX, birdY;

    public GameView(Context context){
        super(context);
        handler = new Handler();
        runnable =  new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        display = (((Activity)getContext()).getWindowManager().getDefaultDisplay());
        point = new Point();
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;
        rect = new Rect(0, 0, dWidth, dHeight);
        birds = new Bitmap[2];
        birds[0] = BitmapFactory.decodeResource(getResources(), R.drawable.bird1);
        birds[1] = BitmapFactory.decodeResource(getResources(), R.drawable.bird2);
        birdX = dWidth/2 - birds[0].getWidth()/2;
        birdY = dHeight/2 - birds[0].getHeight()/2;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        handler.postDelayed(runnable, UPDATE_SECONDS);
        canvas.drawBitmap(background, null, rect, null);
        if (birdFrame == 0){
            birdFrame = 1;
        }else {
            birdFrame = 0;
        }

        canvas.drawBitmap(birds[birdFrame], birdX, birdY, null);
    }
}
