package com.baitian.daisongsong.demo.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by daisongsong on 2015/8/3.
 */
public class MyLayout extends LinearLayout {
    public static int mTimes = 0;

    public MyLayout(Context context) {
        super(context);
    }

    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            mTimes = 0;
        }
        if (mTimes < 10) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return true;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mTimes++;
        Log.e(getClass().getSimpleName(), "mTimes=" + MyLayout.mTimes + " " + event);
        return false;
    }
}
