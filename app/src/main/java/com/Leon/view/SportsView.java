package com.Leon.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.Rect;
import android.graphics.RectF;

import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author leixinqiao
 * @create on 2019-11-07 17:13
 * 自定义View - 运动圆环
 */
public class SportsView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final float RADIUS = UiUtil.dp2px(150);
    private RectF mRectF = new RectF();

    private Paint.FontMetrics mFontMetrics=new Paint.FontMetrics();
    private Rect mTextRect = new Rect();

    {
        mPaint.setTextSize(UiUtil.dp2px(50));
        mPaint.setTextAlign(Paint.Align.CENTER);//设置字体横向居中  //纵向居中需要自己计算
    }

    public SportsView(Context context) {
        super(context);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mRectF.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(UiUtil.dp2px(15));
        mPaint.setColor(Color.parseColor("#CCCCCC"));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, mPaint);

        //绘制进度圆环
        mPaint.setColor(Color.parseColor("#FFB5C9"));
        mPaint.setStrokeCap(Paint.Cap.ROUND); //设置圆头
        canvas.drawArc(mRectF, -90, 220, false, mPaint);

        //绘制中心虚线
        mPaint.setColor(Color.parseColor("#FF908F8F"));
        RectF rect = new RectF(0, 0, (int) UiUtil.dp2px(8), (int) UiUtil.dp2px(1));
        Path path = new Path();
        path.addRect(rect, Path.Direction.CW);
        mPaint.setPathEffect(new PathDashPathEffect(path, UiUtil.dp2px(15), 0, PathDashPathEffect.Style.ROTATE));
        canvas.drawLine(getWidth() / 2 - RADIUS, getHeight() / 2, getWidth() / 2 + RADIUS, getHeight() / 2, mPaint);
        mPaint.setPathEffect(null);

        // 绘制文字
        mPaint.setStyle(Paint.Style.FILL);
        float textOffsetY;

        //这里计算出来的是文字的事实baseLine基线
        mPaint.getTextBounds("运动圆环ab运动圆环ab运动圆环ab运动圆环ab", 0, "运动圆环ab运动圆环ab运动圆环ab运动圆环ab".length(), mTextRect);
        textOffsetY = (mTextRect.top + mTextRect.bottom) / 2;

        //使用FontMetrics就可以保证文字改变时 基线不会跳动
        mPaint.getFontMetrics(mFontMetrics);
        textOffsetY = (mFontMetrics.top + mFontMetrics.bottom) / 2;

        canvas.drawText("运动圆环ab运动圆环ab运动圆环ab运动圆环ab", getWidth() / 2, getHeight() / 2 - textOffsetY, mPaint);




    }
}
