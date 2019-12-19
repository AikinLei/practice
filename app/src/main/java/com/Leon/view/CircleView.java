package com.Leon.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author leixinqiao
 * @create on 2019-11-15 11:44
 */
public class CircleView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public float mRadius =  UiUtil.dp2px(100);

    {
        mPaint.setColor(Color.parseColor("#FF0000"));
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

//        mRadius = ((Math.min(getWidth(), getHeight()) - UiUtil.dp2px(200)) / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.parseColor("#FFcccc"));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, mPaint);
    }

    public void setMRadius(float radius) {
        this.mRadius = radius;
        invalidate();
    }

    public float getMRadius() {
        return this.mRadius;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int size=Math.min(measuredWidth,measuredHeight);
        Log.i("CircleView", "onMeasure: "+size);
        setMeasuredDimension(size,size);
    }
}
