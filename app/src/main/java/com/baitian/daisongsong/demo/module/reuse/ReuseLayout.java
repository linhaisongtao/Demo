package com.baitian.daisongsong.demo.module.reuse;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by daisongsong on 2015/8/14.
 */
public class ReuseLayout extends LinearLayout {
    public ReuseLayout(Context context) {
        super(context);
    }

    public ReuseLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReuseLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
