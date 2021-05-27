package com.example.flappybird;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

public class StartGame extends Activity {

    GameView gameView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);

        GameView.startGameContext = this;
        setContentView(gameView);
    }
}
