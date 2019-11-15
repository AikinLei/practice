package com.Leon.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * @author leixinqiao
 * @create on 2019-09-26 18:40
 */
public class DragView extends View {


    private float mStartX;
    private float mStartY;


    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


//    private float downX,downY;
//    private int width,height,maxWidth,maxHeight;
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        // 获取屏宽高 和 可是适用范围 （我的需求是可在屏幕内拖动 不超出范围 也不需要隐藏）
//        width=getMeasuredWidth();
//        height=getMeasuredHeight();
//        maxWidth = getMaxWidth(getContext());
//        maxHeight = getMaxHeight(getContext());// 此时减去状态栏高度 注意如果有状态栏 要减去状态栏 如下行 得到的是可活动的高度
//        //maxHeight = UiUtil.getMaxHeight(context)-getStatusBarHeight() - getNavigationBarHeight();
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        super.onTouchEvent(event);
//        if (this.isEnabled()) {
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN: // 点击动作处理 每次点击时将拖动状态改为 false 并且记录下点击时的坐标 downX downY
//
//                    downX = event.getX(); // 点击触屏时的x坐标 用于离开屏幕时的x坐标作计算
//                    downY = event.getY(); // 点击触屏时的y坐标 用于离开屏幕时的y坐标作计算
//                    break;
//                case MotionEvent.ACTION_MOVE: // 滑动动作处理 记录离开屏幕时的 moveX  moveY 用于计算距离 和 判断滑动事件和点击事件 并作出响应
//                    final float moveX = event.getX() - downX;
//                    final float moveY = event.getY() - downY;
//                    int l,r,t,b; // 上下左右四点移动后的偏移量
//                    //计算偏移量 设置偏移量 = 3 时 为判断点击事件和滑动事件的峰值
//                    if (Math.abs(moveX) > 3 ||Math.abs(moveY) > 3) { // 偏移量的绝对值大于 3 为 滑动时间 并根据偏移量计算四点移动后的位置
//                        l = (int) (getLeft() + moveX);
//                        r = l+width;
//                        t = (int) (getTop() + moveY);
//                        b = t+height;
//                        //不划出边界判断,最大值为边界值
//                        // 如果你的需求是可以划出边界 此时你要计算可以划出边界的偏移量 最大不能超过自身宽度或者是高度  如果超过自身的宽度和高度 view 划出边界后 就无法再拖动到界面内了 注意
//                        if(l<0){ // left 小于 0 就是滑出边界 赋值为 0 ; right 右边的坐标就是自身宽度 如果可以划出边界 left right top bottom 最小值的绝对值 不能大于自身的宽高
//                            l=0;
//                            r=l+width;
//                        }else if(r> maxWidth){ // 判断 right 并赋值
//                            r= maxWidth;
//                            l=r-width;
//                        }
//                        if(t<0){ // top
//                            t=0;
//                            b=t+height;
//                        }else if(b> maxHeight){ // bottom
//                            b= maxHeight;
//                            t=b-height;
//                        }
//                        this.layout(l, t, r, b); // 重置view在layout 中位置
////                        isDrag=true;  // 重置 拖动为 true
//                    }else {
////                        isDrag=false; // 小于峰值3时 为点击事件
//                    }
//                    break;
//                case MotionEvent.ACTION_UP: // 不处理
//                    setPressed(false);
//                    break;
//                case MotionEvent.ACTION_CANCEL: // 不处理
//                    setPressed(false);
//                    break;
//            }
//            return true;
//        }
//        return false;
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                ViewGroup parent = (ViewGroup) getParent();
                int measuredWidth = parent.getMeasuredWidth();
                int height = parent.getHeight();

                float x = event.getX();
                float y = event.getY();

                float moveX = x - mStartX;
                float moveY = y - mStartY;

                int left = (int) (getLeft() + moveX);
                int right = left + getWidth();
                int top = (int) (getTop() + moveY);
                int bottom = top + getHeight();


                if (left < 0) {
                    left = 0;
                    right = left + getWidth();
                } else if (right > measuredWidth) {
                    right = measuredWidth;
                    left = right - getWidth();
                }

                if (top < 0) {
                    top = 0;
                    bottom = top + getWidth();
                } else if (bottom > height) {
                    bottom = height;
                    top = bottom - getHeight();
                }


                layout(left, top, right, bottom);

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                break;
        }


        return true;
    }


    // 获取最大宽度
    public static int getMaxWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    // 获取最大高度
    public static int getMaxHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
}
