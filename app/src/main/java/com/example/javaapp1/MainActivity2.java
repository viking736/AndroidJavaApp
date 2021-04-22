package com.example.javaapp1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addEventListener();
    }

    public void addEventListener(){
        Button btn = (Button) findViewById(R.id.btn1);
        Button btnAlert = (Button) findViewById(R.id.button2);
        Button calc = (Button) findViewById(R.id.button3);
        final MediaPlayer mp3 = MediaPlayer.create(this, R.raw.sound);

        btn.setText("Submit!");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setText("Hi");
                btn.setBackgroundColor(Color.GREEN);
                Toast.makeText(MainActivity2.this, "Some text", Toast.LENGTH_LONG).show();
                mp3.start();
            }
        });

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.example.javaapp1.MainActivity");
                startActivity(i);
            }
        });

        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity2.this);
                alert.setMessage("Are you sure? ").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert1 = alert.create();
                alert1.setTitle("ALERT!!!");
                alert1.show();
            }
        });
    }
}