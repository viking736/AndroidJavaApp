package com.example.paintingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PaintingView extends View {

    private CustomPath drawPath;
    private Bitmap canvasBitmap;
    private Paint paint;
    private Paint canvasPaint;
    private float brushSize = 0f;
    private int color = Color.BLACK;
    private Canvas canvas;
    private final ArrayList<CustomPath> paths = new ArrayList<>();

    public PaintingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpDrawing();
    }

    public static class CustomPath extends Path {
        private int colour;
        private float brushThickness;
        public CustomPath(int colour, float brushThickness){
            this.colour = colour;
            this.brushThickness = brushThickness;
        }
    }

    public void setUpDrawing(){
        paint = new Paint();
        drawPath = new CustomPath(color, brushSize);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
        brushSize = 20f;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(canvasBitmap, 0f, 0f, paint);

        for (CustomPath path : paths){
            paint.setStrokeWidth(path.brushThickness);
            paint.setColor(path.colour);
            canvas.drawPath(path, paint);
        }

        if (drawPath != null){
            paint.setStrokeWidth(drawPath.brushThickness);
            paint.setColor(drawPath.colour);
            canvas.drawPath(drawPath, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                drawPath.colour = color;
                drawPath.brushThickness = brushSize;

                drawPath.reset();
                drawPath.moveTo(x, y);

                break;
            case MotionEvent.ACTION_MOVE:

                drawPath.lineTo(x, y);

                break;
            case MotionEvent.ACTION_UP:
                paths.add(drawPath);

                drawPath = new CustomPath(color, brushSize);

                break;
            default:

                return false;
        }
        invalidate();
        return true;
    }
}
