package com.Leon.view.coordinator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author leixinqiao
 * @create on 2019-10-21 11:55
 */
public class DragView2 extends View {
    public DragView2(Context context) {
        super(context);
    }

    public DragView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float X ;
    float Y ;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                X = event.getX();
                Y = event.getY();

                break;

            case MotionEvent.ACTION_MOVE:
                float offestX = event.getX() - X;
                float offestY = event.getY() - Y;

                Log.i("Lei", "onTouchEvent: offestX : " + offestX + "  x: " + X + "  eventX: " + event.getX());
                Log.i("Lei", "onTouchEvent: offestY : " + offestY + "  x: " + Y + "  eventY: " + event.getY());


                offsetLeftAndRight((int) offestX);
                offsetTopAndBottom((int) offestY);

//                setTranslationX(getX()+offestX);
//                setTranslationY(getY()+offestY);

                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }


        return true;
    }
}
