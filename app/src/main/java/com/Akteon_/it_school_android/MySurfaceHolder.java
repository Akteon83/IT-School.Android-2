package com.Akteon_.it_school_android;

import static java.lang.Math.abs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.DrawableWrapper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import javax.xml.transform.Transformer;

public class MySurfaceHolder extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private final DrawThread drawThread;

    public MySurfaceHolder(Context context) {
        super(context);
        getHolder().addCallback(this);
        drawThread = new DrawThread();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        this.surfaceHolder = holder;
        drawThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    MyCircle myCircle;

    class DrawThread extends Thread {
        private volatile boolean running = true;
        @Override
        public void run() {
            Canvas canvas;
            myCircle = new MyCircle(getWidth() / 2f, getHeight() / 2f);
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.parseColor("#8080FF"));
            myCircle.draw(canvas);
            surfaceHolder.unlockCanvasAndPost(canvas);
            while(running) {
                try {
                    Thread.sleep(100);
                    canvas = surfaceHolder.lockCanvas();
                    canvas.drawColor(Color.parseColor("#8080FF"));
                    myCircle.update();
                    myCircle.draw(canvas);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                } catch (Exception e) {

                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        myCircle.tx = event.getX();
        myCircle.ty = event.getY();
        return super.onTouchEvent(event);
    }

    class MyCircle {
        float cx, cy, tx, ty;
        Paint paint;

        public MyCircle(float cx, float cy) {
            this.cx = cx;
            this.cy = cy;
            tx = cx;
            ty = cy;
            paint = new Paint();
            paint.setColor(Color.parseColor("#FF0000"));
        }

        void draw(Canvas canvas) {
            canvas.drawCircle(cx, cy, 50, paint);
        }

        void update() {
            if (tx != cx) {
                if (abs(tx - cx) >= 20) {
                    cx += (tx - cx) / abs(tx - cx) * 20;
                } else {
                    cx = tx;
                }
            } else if (ty != cy) {
                if (abs(ty - cy) >= 20) {
                    cy += (ty - cy) / abs(ty - cy) * 20;
                } else {
                    cy = ty;
                }
            }
        }
    }
}
