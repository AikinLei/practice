package com.Leon;

import android.support.annotation.NonNull;

/**
 * @author leixinqiao
 * @create on 2019/3/1 下午9:36
 */
public class ClassBean {


    private Class<?> mClass;
    private String mTitle;

    public ClassBean(Class<?> aClass, String title) {
        mClass = aClass;
        mTitle = title;
    }

    public Class<?> getClassN() {
        return mClass;
    }

    public void setClass(Class<?> aClass) {
        mClass = aClass;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
