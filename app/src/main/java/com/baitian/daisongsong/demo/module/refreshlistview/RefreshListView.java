package com.baitian.daisongsong.demo.module.refreshlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import junit.framework.Assert;

/**
 * Created by daisongsong on 2015/8/12.
 */
public class RefreshListView extends LinearLayout {
    private ListView mListView;
    private View mHeaderView;

    public RefreshListView(Context context) {
        super(context);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setOrientation(VERTICAL);

        final int childCount = getChildCount();
        Assert.assertEquals(childCount, 2);
        mHeaderView = getChildAt(0);
        mListView = (ListView) getChildAt(1);
    }
}
