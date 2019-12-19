package com.Leon.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author leixinqiao
 * @create on 2019-12-05 09:41
 */
public class ForkView extends View {

    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);


    public ForkView(Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.parseColor("#CCCCCC"));
        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2,mPaint);

        mPaint.setColor(Color.BLACK);

        canvas.save();
        canvas.rotate(40,getWidth()/2,getHeight()/2);
        canvas.drawLine(0+15,getHeight()/2,getWidth()-15,getHeight()/2,mPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(-40,getWidth()/2,getHeight()/2);
        canvas.drawLine(0+15,getHeight()/2,getWidth()-15,getHeight()/2,mPaint);
        canvas.restore();

    }
}
