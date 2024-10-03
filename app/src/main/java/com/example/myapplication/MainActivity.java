package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView drawingArea;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private String selectedShape = "line";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonCreate = findViewById(R.id.buttonCreate);
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonLine = findViewById(R.id.buttonLine);
        Button buttonSquare = findViewById(R.id.buttonSquare);
        Button buttonEllipse = findViewById(R.id.buttonEllipse);
        drawingArea = findViewById(R.id.drawingArea);


        bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setStrokeWidth(5);


        drawingArea.setImageBitmap(bitmap);


        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawRandomShape();
            }
        });


        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCanvas();
            }
        });

        buttonLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedShape = "line";
            }
        });


        buttonSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedShape = "square";
            }
        });


        buttonEllipse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedShape = "ellipse";
            }
        });
    }


    private void drawRandomShape() {
        Random random = new Random();


        paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

        switch (selectedShape) {
            case "line":

                float startX = random.nextInt(canvas.getWidth());
                float startY = random.nextInt(canvas.getHeight());
                float stopX = random.nextInt(canvas.getWidth());
                float stopY = random.nextInt(canvas.getHeight());
                canvas.drawLine(startX, startY, stopX, stopY, paint);
                break;

            case "square":

                float left = random.nextInt(canvas.getWidth() - 100);
                float top = random.nextInt(canvas.getHeight() - 100);
                float right = left + 100;
                float bottom = top + 100;
                canvas.drawRect(left, top, right, bottom, paint);
                break;

            case "ellipse":

                float cx = random.nextInt(canvas.getWidth());
                float cy = random.nextInt(canvas.getHeight());
                float rx = random.nextInt(100) + 50;
                float ry = random.nextInt(100) + 50;
                canvas.drawOval(cx - rx, cy - ry, cx + rx, cy + ry, paint);
                break;
        }


        drawingArea.invalidate();
    }


    private void clearCanvas() {
        canvas.drawColor(Color.WHITE);
        drawingArea.invalidate();
    }
}
