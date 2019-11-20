package com.Leon.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author leixinqiao
 * @create on 2019-11-19 16:27
 */
public class DrawableView extends View {
    Drawable mDrawable;  //一个抽象的功能单一view

    {
        mDrawable = new CustomDrawable();

    }

    public DrawableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mDrawable.setBounds(100, 100, getWidth(), getHeight());
        mDrawable.draw(canvas);

    }
}

/*
*   1. Drawable是什么
*       Drawable 是一个抽象的功能单一的View
*
*
*
* */
