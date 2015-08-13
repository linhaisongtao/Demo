package com.baitian.daisongsong.demo.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by daisongsong on 2015/8/13.
 */
public class SwipeBackLayout extends FrameLayout {
    private static final String TAG = "SwipeBackLayout";

    private static final int MAX_X_PROCESS = 200;
    private static final int MIN_DX_TO_FINISH = 200;

    private int mFirstX = Integer.MIN_VALUE;

    private int mWindowWith = Integer.MIN_VALUE;

    public SwipeBackLayout(Context context) {
        super(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        mWindowWith = displayMetrics.widthPixels;
    }


    public SwipeBackLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeBackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean consume = false;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                if (x < MAX_X_PROCESS) {
                    consume = true;
                    mFirstX = x;
                }
                Log.d(TAG, "consume=" + consume);
                break;
            case MotionEvent.ACTION_MOVE:
                int dX = (int) (event.getX() - mFirstX);
                move(dX);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                positionToSuitPosition(event);
                break;

        }

        return consume || super.onTouchEvent(event);
    }

    private void positionToSuitPosition(MotionEvent event) {
        int dX = (int) (event.getX() - mFirstX);
        Log.d(TAG, "dX=" + dX);
        if (dX > MIN_DX_TO_FINISH) {
            offsetLeftAndRight(mWindowWith);
            if(mSwipeBackListener != null){
                mSwipeBackListener.finishActivity();
            }
        } else {
            requestLayout();
        }
    }

    private void move(int dX) {
        offsetLeftAndRight(dX);
    }

    public static interface SwipeBackListener{
        void finishActivity();
    }

    private SwipeBackListener mSwipeBackListener;

    public void setSwipeBackListener(SwipeBackListener swipeBackListener) {
        mSwipeBackListener = swipeBackListener;
    }
}
