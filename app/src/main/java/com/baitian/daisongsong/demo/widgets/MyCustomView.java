package com.baitian.daisongsong.demo.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by daisongsong on 2015/7/31.
 *sdsds
 * modified by daisongsong on 2015/7/31.
 */
public class MyCustomView extends View {
    private static final String TAG = "MyCustomView";

    public MyCustomView(Context context) {
        this(context, null);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw");

        int height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();

        int x = getPaddingLeft();
        int y = getPaddingTop();

        int centerX = width / 2 + x;
        int centerY = height / 2 + y;


        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(2);
        p.setAntiAlias(true);

        p.setColor(Color.RED);
        canvas.drawCircle(centerX, centerY, Math.min(width, height) / 2 - 1, p);

        p.setColor(Color.YELLOW);
        canvas.drawCircle(centerX, centerY, Math.max(width, height) / 2 - 1, p);

        p.setColor(Color.BLUE);
        canvas.drawRect(x, y, x + width, y + height, p);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(160 + getPaddingLeft() + getPaddingRight(), 90 + getPaddingTop() + getPaddingBottom());
        Log.e(TAG, "onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e(TAG, "c=" + changed + " ,l=" + left + ",t=" + top + ",r=" + right + ",b=" + bottom);
        super.onLayout(changed, left, top, right, bottom);
    }
}
