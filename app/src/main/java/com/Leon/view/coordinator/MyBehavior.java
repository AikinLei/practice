package com.Leon.view.coordinator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author leixinqiao
 * @create on 2019-10-21 12:24
 */

public class MyBehavior extends CoordinatorLayout.Behavior {

    public MyBehavior() {
    }

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof DragView2;
    }


    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {

        int left = dependency.getLeft();
        int top = dependency.getTop();

        int width = parent.getWidth();

        child.layout(width - left, top, width - left + child.getWidth(), top + child.getHeight());

        return true;
    }


}
