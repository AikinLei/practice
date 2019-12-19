package com.Leon.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author leixinqiao
 * @create on 2019-11-27 16:37
 */
public class TabLayout extends ViewGroup {



    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int width=0;
        int height=0;

        int lineUsedWidth=0;
        int lineHeight=0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

//            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);

            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.topMargin;
            int childHeight = child.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;


            if (lineUsedWidth+childWidth>sizeWidth){
                lineUsedWidth=childWidth;
                width=Math.max(lineUsedWidth,width);
                height+=lineHeight;
                lineHeight=childHeight;

            }else {

                lineUsedWidth=lineUsedWidth+childWidth;
                lineHeight=Math.max(childHeight,lineHeight);

            }

            if (i==getChildCount()-1){
                width=Math.max(lineUsedWidth,width);
                height=lineHeight+height;
            }

        }

        setMeasuredDimension(
              modeWidth==MeasureSpec.EXACTLY? sizeWidth: width,

                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height);

    }




    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int width = getWidth();

        int childCount = getChildCount();

        int lineWidthUsed=0;
        int topUsed=0;


        int lineMaxHeight=0;

        for (int i = 0; i < childCount; i++) {

            View child = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();

            int leftMargin = layoutParams.leftMargin;
            int rightMargin = layoutParams.rightMargin;
            int topMargin = layoutParams.topMargin;
            int bottomMargin = layoutParams.bottomMargin;

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();



            if (lineWidthUsed+childWidth+ leftMargin + rightMargin >width){
                lineWidthUsed=0;

                topUsed+=lineMaxHeight;

                lineMaxHeight=childHeight+topMargin+bottomMargin;

                child.layout(lineWidthUsed+ leftMargin,topUsed+ topMargin,
                        lineWidthUsed+ leftMargin +childWidth,topUsed+ topMargin +childHeight);


                lineWidthUsed+=childWidth+ leftMargin + rightMargin;

            }else {
                child.layout(lineWidthUsed+ leftMargin,topUsed+ topMargin,lineWidthUsed+ leftMargin +childWidth,topUsed+ topMargin +childHeight);
                lineWidthUsed+=childWidth+ leftMargin + rightMargin;

                lineMaxHeight=Math.max(lineMaxHeight,childHeight+topMargin+bottomMargin);
            }
        }

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

}
