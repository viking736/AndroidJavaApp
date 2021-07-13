package com.example.paintingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private PaintingView paintingView;
    private ImageButton imageButton, imageButtonCurrentPaint;

    private LinearLayout linearLayout;

    private SensorManager sensorManager;
    private boolean isDefaultColor = false;
    private long lastUpdate;
    private boolean hasFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton = (ImageButton) findViewById(R.id.image_button);
        paintingView = (PaintingView) findViewById(R.id.painting_view);
        paintingView.setBackgroundColor(Color.WHITE);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();

        linearLayout = (LinearLayout) findViewById(R.id.linear_layout_paint_colours);
        imageButtonCurrentPaint = (ImageButton) linearLayout.getChildAt(1);

        if (imageButtonCurrentPaint != null)
            imageButtonCurrentPaint.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.pallet_selected));

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBrushSize();
            }
        });
    }

    private void showBrushSize() {
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

    public void palletClicked(View view) {
        if (view != imageButtonCurrentPaint) {
            ImageButton button = (ImageButton) view;
            String colourTag = button.getTag().toString();
            paintingView.setColour(colourTag);

            button.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.pallet_selected));

            if (imageButtonCurrentPaint != null) {
                imageButtonCurrentPaint.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,
                        R.drawable.pallet));
                imageButtonCurrentPaint = (ImageButton) view;
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void getAccelerometer(SensorEvent event) {
        float[] values = event.values;

        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelerationSquareRoot = (float) ((x * x + y * y + z * z) / (Math.pow(SensorManager.GRAVITY_EARTH, 2)));
        long actualTime = System.currentTimeMillis();

        if (accelerationSquareRoot >= 2) {
            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;
            if (isDefaultColor) {
                hasFlash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
                paintingView.setBackgroundColor(Color.WHITE);
            } else {
                paintingView.setBackgroundColor(Color.BLACK);
            }
            isDefaultColor = !isDefaultColor;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(MainActivity.this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
}