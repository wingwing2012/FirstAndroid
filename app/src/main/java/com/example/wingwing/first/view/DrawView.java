package com.example.wingwing.first.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * TODO: document your custom view class.
 */
public class DrawView extends View {

    public float currentX = 40;
    public float currentY = 50;
    Paint p = new Paint();

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
     }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        p.setColor(Color.RED);

        canvas.drawCircle(currentX, currentY, 15, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = false;
        if (null != event) {
            currentX = event.getX();
            currentY = event.getY();
            invalidate();
            result = true;
        }
        return result;
    }
}
