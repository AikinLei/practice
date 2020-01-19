package com.Leon.view.swipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * @author leixinqiao
 * @create on 2020-01-19 10:21
 */
public class VerticalSwipeLayout2 extends LinearLayout {

    private static String TAG = "VerticalSwipeLayout";

    private ViewDragHelper mDragHelper;

    private View mTopView;
    private View mBottomView;

    private int mRang;

    private boolean isOpen = true;

    public VerticalSwipeLayout2(@NonNull Context context) {
        super(context);
    }

    public VerticalSwipeLayout2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalSwipeLayout2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDragHelper = ViewDragHelper.create(this, mViewDragHelperCallBack);
    }


    //    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        int childCount = getChildCount();
//        int heightUsed = 0;
//        int widthUsed = 0;
//        for (int i = 0; i < childCount; i++) {
//            View child = getChildAt(i);
//            measureChild(child, widthMeasureSpec, heightMeasureSpec);
//            int measuredHeight = child.getMeasuredHeight();
//            int childMeasuredWidth = child.getMeasuredWidth();
//
//            heightUsed += measuredHeight;
//            widthUsed = Math.max(widthUsed, childMeasuredWidth);
//        }
//
//        setMeasuredDimension(widthUsed, heightUsed);
//    }
//
//
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        int childCount = getChildCount();
//        int heightUsed = 0;
//
//        for (int i = 0; i < childCount; i++) {
//            View child = getChildAt(i);
//            child.layout(left, heightUsed, right, heightUsed + child.getMeasuredHeight());
//
//            if (i == 0) {
//                mRang = child.getMeasuredHeight();
//
//            }
//
//            heightUsed += child.getMeasuredHeight();
//        }

        super.onLayout(changed, left, top, right, bottom);
        mRang = getChildAt(0).getMeasuredHeight();
        Log.i(TAG, "onLayout: range::  " + mRang);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTopView = getChildAt(0);
        mBottomView = getChildAt(1);
        Log.i(TAG, "onFinishInflate: ");
    }

    private ViewDragHelper.Callback mViewDragHelperCallBack = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View view, int i) {
            Log.i(TAG, "tryCaptureView: view:  " + view.toString());
            if (view == mBottomView) {
                return true;
            }
            return false;
        }


        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            return 0;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            return top;
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
        }
    };


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }
}
