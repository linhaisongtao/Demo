package com.baitian.daisongsong.demo.module.refreshlistview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import junit.framework.Assert;

/**
 * Created by daisongsong on 2015/8/12.
 */
public class RefreshListView extends LinearLayout {
    private View mHeaderView;
    private RecyclerView mRecyclerView;

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
        mRecyclerView = (RecyclerView) getChildAt(1);
    }
}
