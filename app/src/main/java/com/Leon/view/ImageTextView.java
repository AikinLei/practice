package com.Leon.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import android.graphics.RectF;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.Leon.RetrofitPratice.R;

/**
 * @author leixinqiao
 * @create on 2019-11-08 16:46
 * 绘制多行文字
 */
public class ImageTextView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    PorterDuffXfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private int radius = (int) UiUtil.dp2px(60);

    RectF mSaveRecf = new RectF();

    {

    }

    public ImageTextView(Context context) {
        super(context);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mSaveRecf.set(getWidth() / 2 - radius, getHeight() / 2 - radius, getWidth() / 2 + radius, getHeight() / 2 + radius);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap smallBitmap = getSmallBitmap((int) UiUtil.dp2px(160));
        int width = smallBitmap.getWidth();
        int height = smallBitmap.getHeight();

        Log.i("Lei", "onDraw: width： " + width + "    height :" + height);

        int saved = canvas.saveLayer(mSaveRecf, mPaint);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, UiUtil.dp2px(60), mPaint);
        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(smallBitmap, getWidth() / 2 - width / 2, getHeight() / 2 - height / 2, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(saved);
    }


    private Bitmap getSmallBitmap(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.icon_autor, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth; // inDensity 和 inTargetDesity 值不同时  最终会加载出 inTargetDensity的尺寸
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.mipmap.icon_autor, options);

    }

}
