package com.Leon.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author leixinqiao
 * @create on 2019-11-19 16:37
 */
public class CustomDrawable extends Drawable {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int INTERAL = (int) UiUtil.dp2px(80);

    {
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(UiUtil.dp2px(1));
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();

        for (int i = bounds.top; i < bounds.right; i += INTERAL) {
            canvas.drawLine(i, bounds.left, i, bounds.bottom, mPaint);
        }

        for (int i = bounds.left; i < bounds.bottom; i += INTERAL) {
            canvas.drawLine(bounds.left, i, bounds.right, i, mPaint);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public int getAlpha() {
        return mPaint.getAlpha();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        //TRANSLUCENT --- 半透明
        //TRANSPARENT --- 全透明
        return mPaint.getAlpha() == 0 ? PixelFormat.TRANSPARENT :
                mPaint.getAlpha() == 0xFF ? PixelFormat.OPAQUE : PixelFormat.TRANSLUCENT;
    }
}
