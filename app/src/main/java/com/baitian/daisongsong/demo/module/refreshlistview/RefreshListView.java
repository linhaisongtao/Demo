package com.baitian.daisongsong.demo.module.refreshlistview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import junit.framework.Assert;

/**
 * Created by daisongsong on 2015/8/12.
 */
public class RefreshListView extends LinearLayout {
    private static final String TAG = "RefreshListView";

    private View mHeaderView;
    private RecyclerView mRecyclerView;
    private int mFirstY = Integer.MIN_VALUE;
    private int mFirstComletelyVisibleItemPosition = Integer.MIN_VALUE;

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

        mHeaderHeight = mHeaderView.getLayoutParams().height;
    }

    private int mHeaderHeight = Integer.MIN_VALUE;

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            mFirstComletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
        } else {

        }

        if(mFirstComletelyVisibleItemPosition == 0){

            if(mFirstY == Integer.MIN_VALUE){
                mFirstY = (int) ev.getY();
            }else {
                int dY = (int) (ev.getY() - mFirstY);

                MarginLayoutParams lp = (MarginLayoutParams) mRecyclerView.getChildAt(0).getLayoutParams();
                lp.topMargin = dY;
                requestLayout();
            }

        }else {
            mFirstY = Integer.MIN_VALUE;
        }



        return super.onInterceptTouchEvent(ev);
    }
}
