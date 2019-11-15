package com.Leon.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.Leon.RetrofitPratice.R;

/**
 * @author leixinqiao
 * @create on 2019-11-07 14:56
 * 自定义View - 饼状图
 */
public class PieChart extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF mRectF = new RectF();
    private float mRadius = UiUtil.dp2px(150);


    public PieChart(Context context) {
        super(context);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF.set(getWidth() / 2 - mRadius, getHeight() / 2 - mRadius, getWidth() / 2 + mRadius, getHeight() / 2 + mRadius);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        mPaint.setColor(Color.parseColor("#FF0000"));
        mPaint.setStyle(Paint.Style.FILL);
        float dx1 = (float) (Math.cos(Math.toRadians(50)) * 20);
        float dy1 = (float) (Math.sin(Math.toRadians(50)) * 20);
        canvas.translate(dx1, dy1);

        canvas.drawArc(mRectF, 20, 60, true, mPaint);
        canvas.restore();

        canvas.save();
        mPaint.setColor(Color.parseColor("#FFcccc"));

        float dx2 = (float) (Math.cos(Math.toRadians(125)) * 20);
        float dy2 = (float) (Math.sin(Math.toRadians(125)) * 20);
        canvas.translate(dx2, dy2);
        canvas.drawArc(mRectF, 80, 90, true, mPaint);
        canvas.restore();

        canvas.save();
        mPaint.setColor(getResources().getColor(R.color.colorAccent));

        float dx3 = (float) (Math.cos(Math.toRadians(215)) * 20);
        float dy3 = (float) (Math.sin(Math.toRadians(215)) * 20);
        canvas.translate(dx3, dy3);

        canvas.drawArc(mRectF, 170, 90, true, mPaint);
        canvas.restore();

        canvas.save();
        mPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));
        float dx4 = (float) (Math.cos(Math.toRadians(320)) * 20);
        float dy4 = (float) (Math.sin(Math.toRadians(320)) * 20);
        canvas.translate(dx4, dy4);

        canvas.drawArc(mRectF, 260, 120, true, mPaint);
        canvas.restore();


        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.parseColor("#000000"));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius + 20, mPaint);

    }


}
