package com.Leon.view.swipe;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class SwipeMenuLayout extends ViewGroup {


    public static final String TAG = "CustomerView";
    private Scroller mScroller;

    public SwipeMenuLayout(Context context) {
        super(context);
        init();
    }

    public SwipeMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwipeMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int mScaleTouchSlop;

    private void init() {
        mScaleTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mScroller = new Scroller(getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setClickable(true);
        int maxHeight = 0;
        int width = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == GONE) continue;

            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);

            int measuredWidth = childAt.getMeasuredWidth();
            width += measuredWidth;

            maxHeight = Math.max(childAt.getMeasuredHeight(), maxHeight);
        }

        Log.i(TAG, "onMeasure: parentWidth   ---  >" + width);
        setMeasuredDimension(width, maxHeight);
    }

    private int mScrollRange;
    private static boolean isTouching;

    private static SwipeMenuLayout mViewCache;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mScrollRange = 0;

        int childCount = getChildCount();
        int left = 0;
        for (int i = 0; i < childCount; i++) {

            View childAt = getChildAt(i);

            childAt.layout(left, 0, left + childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
            left += left + childAt.getMeasuredWidth();

            if (i != 0) {
                mScrollRange += childAt.getMeasuredWidth();
            }
            Log.i(TAG, "onLayout:  range  --- >" + mScrollRange);

        }

    }


    private PointF mFirstP = new PointF();
    //上一次的xy
    private PointF mLastP = new PointF();

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEvent: MotionEvent   EV  --- > " + ev);
        acquireVelocityTracker(ev);

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (isTouching) {
                    return false; //有其他的view 正在touching  就不进入到下面的move方法了  解决多个手指触控的问题
                }
                isTouching = true;

                if (mViewCache != null) {
                    if (mViewCache != this) {
                        mViewCache.quickClose();
                    }
                }

                mLastP.set(ev.getRawX(), ev.getRawY());
                mFirstP.set(ev.getRawX(), ev.getRawY());//2016 11 03 add,判断手指起始落点，如果距离属于滑动了，就屏蔽一切点击事件。

                break;

            case MotionEvent.ACTION_MOVE:
                float gap = ev.getRawX() - mLastP.x;

                if (Math.abs(gap) > 10 || Math.abs(getScrollX()) > 10) {//2016 09 29 修改此处，使屏蔽父布局滑动更加灵敏，
                    getParent().requestDisallowInterceptTouchEvent(true);
                }


                Log.i(TAG, "dispatchTouchEvent:  gap --- > " + gap);

                scrollBy((int) -gap, 0);


                Log.i(TAG, "dispatchTouchEvent: " + getScrollX());
                //滑动修正
                if (getScrollX() < 0) {
                    scrollTo(0, 0);
                }
                if (getScrollX() > mScrollRange) {
                    scrollTo(mScrollRange, 0);
                }

                mLastP.set(ev.getRawX(), ev.getRawY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                if (Math.abs(getScrollX()) > 0.5 * mScrollRange) {
                    scrollTo(mScrollRange, 0);
                    mViewCache = this;
                } else {
                    scrollTo(0, 0);
                }

                isTouching = false;
                break;
        }


        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent: " + ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(ev.getRawX() - mFirstP.x) > mScaleTouchSlop) {
                    Log.i(TAG, "onInterceptTouchEvent: move Intercept");
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }


        return super.onInterceptTouchEvent(ev);
    }

    private VelocityTracker mVelocityTracker;

    /**
     * @param event 向VelocityTracker添加MotionEvent
     * @see VelocityTracker#obtain()
     * @see VelocityTracker#addMovement(MotionEvent)
     */
    private void acquireVelocityTracker(final MotionEvent event) {
        if (null == mVelocityTracker) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }


    private void quickClose() {
        mViewCache.scrollTo(0, 0);
        mViewCache = null;
    }

    //每次ViewDetach的时候，判断一下 ViewCache是不是自己，如果是自己，关闭侧滑菜单，且ViewCache设置为null，
    // 理由：1 防止内存泄漏(ViewCache是一个静态变量)
    // 2 侧滑删除后自己后，这个View被Recycler回收，复用，下一个进入屏幕的View的状态应该是普通状态，而不是展开状态。
    @Override
    protected void onDetachedFromWindow() {
        if (this == mViewCache) {
            mViewCache.quickClose();
            mViewCache = null;
        }
        super.onDetachedFromWindow();
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
