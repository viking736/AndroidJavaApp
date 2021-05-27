package com.example.flappybird;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import android.os.Handler;

import java.util.Random;

public class GameView extends View {

    Handler handler;
    Runnable runnable;
    int UPDATE_SECONDS = 30;
    Bitmap background;
    Display display;
    Point point;
    int dWidth, dHeight;
    Rect rect;
    Bitmap[] birds;
    int birdFrame = 0;
    int birdX, birdY;
    int speed = 0;
    int gravity = 6;
    boolean gameState = false;

    int gap = 300;
    int minTubeOffset, maxTubeOffset;
    int numberOfTubes = 100;
    int distanceBetweenTubes;
    int[] tubeX = new int[numberOfTubes];
    int[] tubeY = new int[numberOfTubes];
    Bitmap topTube, bottomTube;
    Random random;
    int score = 0;
    Paint scorePaint;

    static Sounds sounds;
    static Context startGameContext;

    int tubeVelocity = 8;


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
        topTube = BitmapFactory.decodeResource(getResources(), R.drawable.toptube);
        bottomTube = BitmapFactory.decodeResource(getResources(), R.drawable.bottomtube);

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

        distanceBetweenTubes = dWidth*3/6;
        minTubeOffset = gap/2;
        maxTubeOffset = dHeight - minTubeOffset - gap;

        random = new Random();

        for (int i = 0; i < numberOfTubes; i++){
            tubeX[i] = dWidth + i*distanceBetweenTubes;
            tubeY[i] = minTubeOffset + random.nextInt(maxTubeOffset - minTubeOffset);
        }

        sounds = new Sounds(context);


    }

    public static Sounds getSounds(){
        return sounds;
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

        if (gameState){
            if (birdY < dHeight-birds[0].getHeight()*3 || speed < 0){
                speed += gravity;
                birdY += speed;
            }
            for (int i = 0; i < numberOfTubes; i++ ){
                tubeX[i] -= tubeVelocity;
                canvas.drawBitmap(topTube, tubeX[i], tubeY[i] - topTube.getHeight(), null);
                canvas.drawBitmap(bottomTube, tubeX[i], tubeY[i] + gap, null);

                if (tubeX[i] == dWidth/3){
                    score++;
                    GameView.getSounds().playPoint();
                }
                if (tubeX[i] < birdX + birds[birdFrame].getWidth() && birdX < tubeX[i] + topTube.getWidth() &&
                        birdY + birds[birdFrame].getHeight()*3 > tubeY[i] + bottomTube.getHeight()/2
                        ||
                        tubeX[i] < birdX + birds[birdFrame].getWidth() && birdX < tubeX[i] + topTube.getWidth() &&
                        birdY + birds[birdFrame].getHeight()*1.8 < tubeY[i] + topTube.getHeight()/2 - gap
                )
                {
                    Context context = GameView.startGameContext;
                    Intent intent = new Intent(context, GameOver.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    GameView.getSounds().playHit();
                    gameState = false;


                }
            }
            if (gameState == true){
                GameView.getSounds().playBackground();
            }else {
                GameView.getSounds().stopBackground();
            }

        }

        scorePaint = new Paint();
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(80);
        scorePaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Points: " +  score, 0, 110, scorePaint);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN){
            speed = -30;
            gameState = true;
            GameView.getSounds().playFly();
        }
        return true;
    }
}
