package com.baitian.daisongsong.demo.module.floatview;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.baitian.daisongsong.demo.R;

/**
 * Created by daisongsong on 2015/8/5.
 */
public class FloatManager {
    private Context mContext;

    private FloatSmallView mFloatSmallView;

    private WindowManager mWindowManager;

    private WindowManager.LayoutParams lp;

    private int screenWidth = 0;
    private int screenHeight = 0;
    private int width = 0;
    private int height = 0;
    private boolean mShow = false;

    public FloatManager(Context context) {
        mContext = context;
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;


        mFloatSmallView = new FloatSmallView(context);
        TextView textView = (TextView) mFloatSmallView.findViewById(R.id.mTextViewPercent);
        width = textView.getLayoutParams().width;
        height = textView.getLayoutParams().height;

        setLayoutParams(mFloatSmallView.getWMLayoutParam());
    }

    public boolean isShowSmallView() {
        return mFloatSmallView != null && mFloatSmallView.getVisibility() == View.VISIBLE;
    }

    public void refreshPercent(float percent) {
        if (mFloatSmallView != null) {
            TextView textView = (TextView) mFloatSmallView.findViewById(R.id.mTextViewPercent);
            textView.setText(String.format("%.2f%%", percent * 100));
            if (!mShow) {
                addSmallView();
                mShow = true;
            }
        }
        updateSmallView();
    }

    public void setLayoutParams(WindowManager.LayoutParams lp) {
        this.lp = lp;
    }

    private void addSmallView() {
        mWindowManager.addView(mFloatSmallView, lp);
    }

    private void updateSmallView() {
        mWindowManager.updateViewLayout(mFloatSmallView, lp);
    }
}
