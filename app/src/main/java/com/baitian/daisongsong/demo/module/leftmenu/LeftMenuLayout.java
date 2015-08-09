package com.baitian.daisongsong.demo.module.leftmenu;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import junit.framework.Assert;


/**
 * Created by daisongsong on 2015/8/6.
 */
public class LeftMenuLayout extends LinearLayout {
    @SuppressWarnings("unused")
    private static final String TAG = "LeftMenuLayout";

    private static final int CHILD_COUNT = 2;

    private static final int ACTION_START_X = 200;
    private static final long INTERVAL = 500;

    private View mMenuView;
    private View mContentView;
    private int mFirstX = 0;
    private int mMenuOriginMarginLeft = 0;
    private int mContentOriginMarginRight = 0;

    private boolean mIsAnimator = false;

    public LeftMenuLayout(Context context) {
        this(context, null, 0);
    }

    public LeftMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        Assert.assertEquals(childCount, CHILD_COUNT);

        mMenuView = getChildAt(0);
        mContentView = getChildAt(1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean consume = true;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFirstX = (int) event.getX();
                if (mFirstX > ACTION_START_X || mIsAnimator) {
                    consume = false;
                } else {
                    LayoutParams lp = (LayoutParams) mMenuView.getLayoutParams();
                    mMenuOriginMarginLeft = lp.leftMargin;

                    LayoutParams lpContent = (LayoutParams) mContentView.getLayoutParams();
                    mContentOriginMarginRight = lpContent.rightMargin;

                }
                break;
            case MotionEvent.ACTION_MOVE:
                placeToPosition((int) (event.getX() - mFirstX));
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                resetToOriginPosition((int) (event.getX() - mFirstX));
                break;
        }
        return consume;
    }

    private void placeToPosition(int dX) {
        LayoutParams lp = (LayoutParams) mMenuView.getLayoutParams();
        lp.leftMargin = mMenuOriginMarginLeft + dX;

        LayoutParams lpContent = (LayoutParams) mContentView.getLayoutParams();
        lpContent.rightMargin = mContentOriginMarginRight - dX;

        requestLayout();
    }

    private void resetToOriginPosition(int dX) {
        ViewWrapper menuViewWrapper = new ViewWrapper(mMenuView);
        ObjectAnimator menuObjectAnimator = ObjectAnimator.ofInt(menuViewWrapper, "leftMargin", dX + mMenuOriginMarginLeft, mMenuOriginMarginLeft).setDuration(INTERVAL);
        menuObjectAnimator.start();

        ViewWrapper contentViewWrapper = new ViewWrapper(mContentView);
        ObjectAnimator contentObjectAnimator = ObjectAnimator.ofInt(contentViewWrapper, "rightMargin", -dX + mContentOriginMarginRight, mContentOriginMarginRight).setDuration(INTERVAL);
        contentObjectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mIsAnimator = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mIsAnimator = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        contentObjectAnimator.start();
    }


    private static class ViewWrapper {
        private View mView;

        public ViewWrapper(View view) {
            mView = view;
        }

        public int getLeftMargin() {
            MarginLayoutParams lp = (MarginLayoutParams) mView.getLayoutParams();
            return lp.leftMargin;
        }

        public void setLeftMargin(int leftMargin) {
            MarginLayoutParams lp = (MarginLayoutParams) mView.getLayoutParams();
            lp.leftMargin = leftMargin;
            mView.requestLayout();
        }

        public int getRightMargin() {
            MarginLayoutParams lp = (MarginLayoutParams) mView.getLayoutParams();
            return lp.rightMargin;
        }

        public void setRightMargin(int rightMargin) {
            MarginLayoutParams lp = (MarginLayoutParams) mView.getLayoutParams();
            lp.rightMargin = rightMargin;
            mView.requestLayout();
        }
    }
}
