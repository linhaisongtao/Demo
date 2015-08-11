package com.baitian.daisongsong.demo.module.leftmenu;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
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
    private static final long INTERVAL = 200;
    private static final int MIN_DISTANCE_TO_CHANGE_STATUS = 300;

    private View mMenuView;
    private View mContentView;
    private int mFirstX = 0;
    private int mMenuOriginMarginLeft = 0;
    private int mContentOriginMarginRight = 0;

    private boolean mIsAnimator = false;
    private boolean mMenuExpanded = false;

    private VelocityTracker mVelocityTracker;
    private int UNIT_OF_VELOCITY = 100;
    private int MIN_VELOCITY_TO_CHANGE_STATUS = 40;

    public LeftMenuLayout(Context context) {
        this(context, null, 0);
    }

    public LeftMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        mVelocityTracker = VelocityTracker.obtain();
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
        mVelocityTracker.addMovement(event);
        if (mMenuExpanded) {
            return doMenuPack(event);
        } else {
            return doMenuExpand(event);
        }
    }

    private boolean doMenuPack(MotionEvent event) {
        boolean consume = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mIsAnimator) {
                    consume = false;
                    break;
                }
                mMenuOriginMarginLeft = ((MarginLayoutParams) mMenuView.getLayoutParams()).leftMargin;
                mContentOriginMarginRight = ((MarginLayoutParams) mContentView.getLayoutParams()).rightMargin;
                mFirstX = (int) event.getX();
                consume = true;
                break;
            case MotionEvent.ACTION_MOVE:
                offsetViews(checkPackDx((int) (event.getX() - mFirstX)));
                consume = true;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                positionToSuitablePosition(checkPackDx((int) (event.getX() - mFirstX)));
                break;
        }
        return consume;
    }

    private int checkPackDx(int dX) {
        dX = Math.max(dX, mContentOriginMarginRight);
        dX = Math.min(0, dX);
        return dX;
    }

    private boolean doMenuExpand(MotionEvent event) {
        boolean consume = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mIsAnimator) {
                    consume = false;
                    break;
                }

                int x = (int) event.getX();
                if (x > ACTION_START_X) {
                    consume = false;
                    break;
                }

                mMenuOriginMarginLeft = ((MarginLayoutParams) mMenuView.getLayoutParams()).leftMargin;
                mContentOriginMarginRight = ((MarginLayoutParams) mContentView.getLayoutParams()).rightMargin;
                consume = true;
                mFirstX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                offsetViews(checkExpandDx((int) (event.getX() - mFirstX)));
                consume = true;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                positionToSuitablePosition(checkExpandDx((int) (event.getX() - mFirstX)));
                break;
        }
        return consume;
    }

    private int checkExpandDx(int dX) {
        dX = Math.min(dX, Math.abs(mMenuOriginMarginLeft));
        dX = Math.max(0, dX);
        return dX;
    }

    private void offsetViews(int dX) {
        MarginLayoutParams lpMenu = (MarginLayoutParams) mMenuView.getLayoutParams();
        lpMenu.leftMargin = mMenuOriginMarginLeft + dX;
        MarginLayoutParams lpContent = (MarginLayoutParams) mContentView.getLayoutParams();
        lpContent.rightMargin = mContentOriginMarginRight - dX;
        requestLayout();
    }

    private void positionToSuitablePosition(int dX) {
        int endMenu = 0;
        int endContent = 0;
        if (mMenuExpanded) {
            if (Math.abs(dX) > MIN_DISTANCE_TO_CHANGE_STATUS) {
                //go
                mMenuExpanded = false;
                endMenu = mContentOriginMarginRight;
                endContent = 0;
            } else {
                //resert
                mVelocityTracker.computeCurrentVelocity(UNIT_OF_VELOCITY);
                if (-mVelocityTracker.getXVelocity() > MIN_VELOCITY_TO_CHANGE_STATUS) {
                    mMenuExpanded = false;
                    endMenu = mContentOriginMarginRight;
                    endContent = 0;
                } else {
                    mMenuExpanded = true;
                    endMenu = 0;
                    endContent = mContentOriginMarginRight;
                }
            }
        } else {
            if (Math.abs(dX) > MIN_DISTANCE_TO_CHANGE_STATUS) {
                //go
                endMenu = 0;
                endContent = mMenuOriginMarginLeft;
                mMenuExpanded = true;
            } else {
                //revert
                mVelocityTracker.computeCurrentVelocity(UNIT_OF_VELOCITY);
                if (mVelocityTracker.getXVelocity() > MIN_VELOCITY_TO_CHANGE_STATUS) {
                    endMenu = 0;
                    endContent = mMenuOriginMarginLeft;
                    mMenuExpanded = true;
                } else {
                    endMenu = mMenuOriginMarginLeft;
                    endContent = 0;
                    mMenuExpanded = false;
                }
            }
        }
        doAnimation(dX, endMenu, endContent);
    }

    private void doAnimation(int dX, int endMenu, int endContent) {
        ViewWrapper menuViewWrapper = new ViewWrapper(mMenuView);
        ObjectAnimator menuObjectAnimator = ObjectAnimator.ofInt(menuViewWrapper, "leftMargin", dX + mMenuOriginMarginLeft, endMenu).setDuration(INTERVAL);
        menuObjectAnimator.start();

        ViewWrapper contentViewWrapper = new ViewWrapper(mContentView);
        ObjectAnimator contentObjectAnimator = ObjectAnimator.ofInt(contentViewWrapper, "rightMargin", -dX + mContentOriginMarginRight, endContent).setDuration(INTERVAL);
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
