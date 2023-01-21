package com.Akteon_.it_school_android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

    float cx;
    float cy;

    float moveX = 0;
    float moveY = 0;

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#FF0000"));
        canvas.drawColor(Color.parseColor("#8080FF"));
        cx = getWidth() / 2f + moveX;
        cy = getHeight() / 2f + moveY;
        canvas.drawCircle(cx, cy, 50, paint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        moveX += (event.getX() - cx);
        moveY += (event.getY() - cy);
        invalidate();
        return super.onTouchEvent(event);
    }
}
