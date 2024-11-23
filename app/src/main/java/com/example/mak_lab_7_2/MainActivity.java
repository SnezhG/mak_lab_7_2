package com.example.mak_lab_7_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    class DrawView extends View {
        Paint p;
        RectF rectf;
        float[] pentagonPoints;
        float[] starPoints;
        float scaleFactor = 1.0f;
        float focusX = 0f;
        float focusY = 0f;
        ScaleGestureDetector scaleGestureDetector;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            rectf = new RectF(700, 100, 800, 150);

            pentagonPoints = new float[]{
                    300, 300, 350, 200,
                    350, 200, 450, 200,
                    450, 200, 500, 300,
                    500, 300, 400, 400,
                    400, 400, 300, 300
            };

            starPoints = new float[]{
                    600, 300, 625, 350,
                    625, 350, 675, 350,
                    675, 350, 640, 380,
                    640, 380, 650, 430,
                    650, 430, 600, 400,
                    600, 400, 550, 430,
                    550, 430, 560, 380,
                    560, 380, 525, 350,
                    525, 350, 575, 350,
                    575, 350, 600, 300
            };

            scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);
            canvas.save();

            canvas.translate(focusX, focusY);
            canvas.scale(scaleFactor, scaleFactor);
            canvas.translate(-focusX, -focusY);

            p.setColor(Color.RED);
            p.setStrokeWidth(15);
            canvas.drawLines(pentagonPoints, p);

            p.setColor(Color.BLUE);
            p.setStrokeWidth(10);
            canvas.drawLines(starPoints, p);

            p.setColor(Color.RED);
            p.setTextSize(36);
            canvas.drawText("MAK_LAB_7", 600, 400, p);

            canvas.restore();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            scaleGestureDetector.onTouchEvent(event);
            return true;
        }

        private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                scaleFactor *= detector.getScaleFactor();

                scaleFactor = Math.max(0.5f, Math.min(scaleFactor, 3.0f));

                focusX = detector.getFocusX();
                focusY = detector.getFocusY();

                invalidate();
                return true;
            }
        }
    }
}
