package com.Leon.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @author leixinqiao
 * @create on 2019-09-26 15:45
 */
public class DragViewGroup extends FrameLayout {

    private View mDragView;
    private float mLastX;
    private float mLastY;

    private DragState mDragState;

    enum DragState {
        IDLE,
        DRAGING
    }

    public DragViewGroup(@NonNull Context context) {
        super(context);
    }

    public DragViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DragViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isPointInViews(event)) {
                    mDragState = DragState.DRAGING;
                    mLastX = event.getX();
                    mLastY = event.getY();
                }
                break;
            case MotionEvent.ACTION_MOVE:

                Log.i("Lei", "onTouchEvent: X " + event.getX());
                Log.i("Lei", "onTouchEvent: Y " + event.getY());

                if (mDragView != null && mDragState == DragState.DRAGING) {
                    float currentX = event.getX();
                    float currentY = event.getY();


                    ViewCompat.offsetLeftAndRight(mDragView, (int) (currentX - mLastX));
                    ViewCompat.offsetTopAndBottom(mDragView, (int) (currentY - mLastY));

                    mLastX = event.getX();
                    mLastY = event.getY();


                }

                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:

                mDragView = null;
                mDragState = DragState.IDLE;
                break;

        }

        return true;
    }


    private boolean isPointInViews(MotionEvent event) {
        boolean result = false;
        float x = event.getX();
        float y = event.getY();

        Rect rect = new Rect();

        for (int i = getChildCount() - 1; i >= 0; i--) {

            View view = getChildAt(i);
            rect.set((int) view.getX(), (int) view.getY(), (int) (view.getX() + view.getWidth()), (int) (view.getY() + view.getHeight()));
            boolean contains = rect.contains((int) x, (int) y);

            if (contains) {
                result = true;
                mDragView = view;
                break;
            }
        }

        return result;
    }
}
