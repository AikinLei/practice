package com.Leon.view;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

/**
 * @author leixinqiao
 * @create on 2019-11-26 14:15
 */
public class MaterialEditText extends AppCompatEditText {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public static final int PADDING_TOP = (int) UiUtil.dp2px(15);
    private static final int TEXT_VERTICAL_OFFEST = (int) UiUtil.dp2px(20);


    private float flatingLabelFraction;
    private boolean tableShown;

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint.setTextSize(UiUtil.dp2px(12));
        setPadding(getPaddingLeft(), getPaddingTop() + PADDING_TOP, getPaddingRight(), getPaddingBottom());

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (tableShown && TextUtils.isEmpty(s)) {
                    getAnimator().reverse();
                    tableShown = false;
                } else if (!tableShown && !TextUtils.isEmpty(s)) {
                    getAnimator().start();
                    tableShown = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private ObjectAnimator alphaAnimator;

    private ObjectAnimator getAnimator() {
        if (alphaAnimator == null) {
            alphaAnimator = ObjectAnimator.ofFloat(this, "flatingLabelFraction", 0, 1);
        }
        return alphaAnimator;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        String hint = getHint().toString();
        mPaint.setAlpha((int) (flatingLabelFraction * 0xFF));
        canvas.drawText(hint, getPaddingLeft(), TEXT_VERTICAL_OFFEST, mPaint);
    }


    public float getFlatingLabelFraction() {
        return flatingLabelFraction;
    }

    public void setFlatingLabelFraction(float flatingLabelFraction) {
        this.flatingLabelFraction = flatingLabelFraction;
        invalidate();
    }

}
