package com.Leon.view.swipe;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class SwipeMenueLayout extends ViewGroup {
    public SwipeMenueLayout(Context context) {
        super(context);
    }

    public SwipeMenueLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeMenueLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

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

        setMeasuredDimension(width, maxHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount = getChildCount();
        int left = 0;                                                                                                                                                                                                                                          
        for (int i = 0; i < childCount; i++) {

            View childAt = getChildAt(i);

            childAt.layout(left, 0, left + childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
            left += left + childAt.getMeasuredWidth();

        }

    }
}
