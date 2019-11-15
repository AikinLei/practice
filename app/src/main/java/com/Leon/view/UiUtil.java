package com.Leon.view;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * @author leixinqiao
 * @create on 2019-11-07 17:15
 */
public class UiUtil {

    public static float dp2px(float dipValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, Resources.getSystem().getDisplayMetrics());
    }
}
