package com.baitian.daisongsong.demo.base;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
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
    private int mLastMoveX = Integer.MIN_VALUE;

    private int mWindowWith = Integer.MIN_VALUE;
    private int mRawX = Integer.MIN_VALUE;
    private SwipeBackListener mSwipeBackListener;
    private ViewGroup mParentView;
    private int mLastX;

    public SwipeBackLayout(Context context) {
        super(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        mWindowWith = displayMetrics.widthPixels;
        init();
    }

    public SwipeBackLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwipeBackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setBackgroundColor(Color.TRANSPARENT);
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
                    mRawX = (int) event.getRawX();
                    mParentView = (ViewGroup) getParent();
                }
                Log.d(TAG, "consume=" + consume);
                break;
            case MotionEvent.ACTION_MOVE:
                mLastMoveX = (int) event.getX();
                Log.d(TAG, "mLastMoveX=" + mLastMoveX);
                int dX = mLastMoveX - mFirstX;


                move((int) (mLastX - event.getRawX()));
                mLastX = (int) event.getRawX();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                positionToSuitPosition(event);
                break;

        }

        return consume || super.onTouchEvent(event);
    }

    private void positionToSuitPosition(MotionEvent event) {
        int dX = (int) (event.getRawX() - mRawX);
        Log.d(TAG, "dX=" + dX);
        if (dX > MIN_DX_TO_FINISH) {
            offsetLeftAndRight(mWindowWith);
            if (mSwipeBackListener != null) {
                mSwipeBackListener.finishActivity();
            }
        } else {
            requestLayout();
        }
    }

    private void move(int dX) {
//        offsetLeftAndRight(dX);
        mParentView.scrollBy(dX, 0);
    }


    public void setSwipeBackListener(SwipeBackListener swipeBackListener) {
        mSwipeBackListener = swipeBackListener;
    }

    public static interface SwipeBackListener {
        void finishActivity();
    }

}
