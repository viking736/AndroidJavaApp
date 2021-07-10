package com.example.paintingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private PaintingView paintingView;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton = (ImageButton) findViewById(R.id.image_button);
        paintingView = (PaintingView) findViewById(R.id.painting_view);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBrushSize();
            }
        });
    }
    private void showBrushSize(){
        Dialog brushDialog = new Dialog(this);
        brushDialog.setContentView(R.layout.dialog_brush_size);
        brushDialog.setTitle("Brush size: ");
        ImageButton smallButton = brushDialog.findViewById(R.id.image_button_small_brush);
        smallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintingView = (PaintingView) findViewById(R.id.painting_view);
                paintingView.setSizeForBrush(10f);
                brushDialog.dismiss();
            }
        });

        ImageButton mediumButton = brushDialog.findViewById(R.id.image_button_medium_brush);
        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintingView = (PaintingView) findViewById(R.id.painting_view);
                paintingView.setSizeForBrush(20f);
                brushDialog.dismiss();
            }
        });

        ImageButton bigButton = brushDialog.findViewById(R.id.image_button_big_brush);
        bigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintingView = (PaintingView) findViewById(R.id.painting_view);
                paintingView.setSizeForBrush(30f);
                brushDialog.dismiss();
            }
        });
        brushDialog.show();
    }
}