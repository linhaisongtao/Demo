package com.baitian.daisongsong.demo.base;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;

/**
 * Created by daisongsong on 2015/8/13.
 */
public class SwipeBackActivity extends Activity {
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    public void setContentView(int layoutResID) {
        mSwipeBackLayout = new SwipeBackLayout(this);
        LayoutInflater.from(this).inflate(layoutResID, mSwipeBackLayout);
        setContentView(mSwipeBackLayout);
        mSwipeBackLayout.setSwipeBackListener(new SwipeBackLayout.SwipeBackListener() {
            @Override
            public void finishActivity() {
                finish();
            }
        });

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().getDecorView().setBackgroundDrawable(null);

    }
}
