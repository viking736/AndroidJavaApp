package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button[] buttons = new Button[9];
    private Button resetGame;

    private int playerOneScoreCount, playerTwoScoreCount, roundCount;
    boolean activePlayer;

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {
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

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                updatePlayerScore();
                playAgain();
                playerStatus.setText("");
            }
        });
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
        roundCount++;

        if (checkWinner()){
            if (activePlayer){
                playerOneScoreCount++;
                updatePlayerScore();
                Toast.makeText(MainActivity.this, "Player one won", Toast.LENGTH_LONG).show();
                playAgain();
            }else {
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(MainActivity.this, "Player two won", Toast.LENGTH_LONG).show();
                playAgain();
            }
        }else if (roundCount == 9){
            playAgain();
        }else {
            activePlayer = !activePlayer;
        }
        if (playerOneScoreCount > playerTwoScoreCount){
            playerStatus.setText("Player one wins");
        }else if (playerTwoScoreCount> playerOneScoreCount){
            playerStatus.setText("Player two wins");
        }else{
            playerStatus.setText("DRAW");
        }
    }

    public boolean checkWinner(){
        boolean winnerResult = false;

        for (int[] winningPosition: winningPositions){
            if (gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2){
                winnerResult = true;
            }
        }
        return winnerResult;
    }

    public void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }

    public void playAgain(){
        roundCount=0;
        activePlayer=true;

        for (int i = 0; i < buttons.length; i++){
            gameState[i] = 2;
            buttons[i].setText("");
        }
    }

}