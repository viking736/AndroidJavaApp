package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button[] buttons = new Button[9];
    private Button resetGame;

    private int playerOneScoreCount, playerTwoScoreCount, roundCount;
    boolean activePlayer;

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPosition = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerOneScore = findViewById(R.id.player_one_score);
        playerTwoScore = findViewById(R.id.player_two_score);
        playerStatus = findViewById(R.id.player_status);

        resetGame = findViewById(R.id.btn_reset);

        for (int i = 0; i < buttons.length; i++){
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }
        roundCount = 0;
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        activePlayer = true;
    }

    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().isEmpty()){
            return;
        }

        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1, buttonID.length()));
        if (activePlayer){
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#FFFFFF"));
            gameState[gameStatePointer] = 0;
        }else{
            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#FFFFFF"));
            gameState[gameStatePointer] = 1;
        }
    }
}