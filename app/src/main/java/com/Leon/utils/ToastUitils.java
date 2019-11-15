package com.Leon.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author leixinqiao
 * @create on 2019-11-15 12:07
 */
public class ToastUitils {

    public static void showToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
