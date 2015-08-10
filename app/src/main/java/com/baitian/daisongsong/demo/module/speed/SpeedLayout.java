package com.baitian.daisongsong.demo.module.speed;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by daisongsong on 2015/8/9.
 */
public class SpeedLayout extends FrameLayout {
    private TextView mTextView;
    private VelocityTracker mVelocityTracker = VelocityTracker.obtain();

    public SpeedLayout(Context context) {
        super(context);
    }

    public SpeedLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpeedLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTextView = new TextView(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(mTextView, lp);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        StringBuilder sb = new StringBuilder();
        mVelocityTracker.computeCurrentVelocity(100);
        sb.append(String.format("XVelocity=%f\n", mVelocityTracker.getXVelocity()));
        sb.append(String.format("YVelocity=%f", mVelocityTracker.getYVelocity()));
        mTextView.setText(sb.toString());
        return true;
    }
}
