package com.baitian.daisongsong.demo.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by daisongsong on 2015/8/3.
 */
public class MyTouchEventView extends View {
    public MyTouchEventView(Context context) {
        super(context);
    }

    public MyTouchEventView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTouchEventView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        MyLayout.mTimes++;
        switch (action){
            case MotionEvent.ACTION_DOWN:
                printMotionEvent(event);
                return true;
            default:
                break;
        }
        printMotionEvent(event);
        return super.onTouchEvent(event);
    }

    private void printMotionEvent(MotionEvent event){
        Log.e(getClass().getSimpleName(), "mTimes=" + MyLayout.mTimes + " " + event);
    }
}
