package com.example.flappybird;

import android.content.Context;
import android.media.MediaPlayer;

public class Sounds {
    Context context;
    MediaPlayer points;
    MediaPlayer fly;
    MediaPlayer hit;
    MediaPlayer background;

    public Sounds(Context context){
        this.context = context;
        points = MediaPlayer.create(context, R.raw.audio_score);
        fly = MediaPlayer.create(context, R.raw.fly);
        hit = MediaPlayer.create(context, R.raw.collision);
        background = MediaPlayer.create(context, R.raw.background);

    }
    public void playPoint(){
        if (points != null){
            points.start();
        }
    }
    public void playFly(){
        if (fly != null){
            fly.start();
        }
    }
    public void playHit(){
        if (hit != null){
            hit.start();
        }
    }
    public void playBackground(){
        if (background != null){
            background.start();
        }
    }
    public void stopBackground(){
        if (background != null){
            background.stop();
        }
    }
}
