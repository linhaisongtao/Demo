package com.baitian.daisongsong.demo.module.floatview;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.baitian.daisongsong.demo.R;

import java.lang.reflect.Field;

/**
 * Created by daisongsong on 2015/8/5.
 */
public class FloatSmallView extends FrameLayout {
    private final int screenHeight;
    private final int screenWidth;
    private WindowManager mWindowManager;
    private int statusBarHeight;

    public FloatSmallView(Context context) {
        this(context, null, 0);
    }

    public FloatSmallView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatSmallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_float_small, this);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
        x= screenWidth;
        y = screenHeight / 2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_MOVE){
            lp.x = (int) event.getRawX();
            lp.y = (int) event.getRawY() - getStatusBarHeight();

            mWindowManager.updateViewLayout(this, lp);
        }

        return true;
    }

    int x;
    int y;

    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
    public WindowManager.LayoutParams getWMLayoutParam(){

        lp.type = WindowManager.LayoutParams.TYPE_PHONE;
        lp.format = PixelFormat.RGBA_8888;
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        lp.gravity = Gravity.LEFT | Gravity.TOP;
        lp.width = 150;
        lp.height = 90;
        lp.x = x;
        lp.y = y;

        return lp;
    }

    private int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }
}
