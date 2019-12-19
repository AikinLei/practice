package com.Leon.threadTest;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author leixinqiao
 * @create on 2019-12-02 10:03
 */
public class MyObserver<Integer> implements Observer<Integer> {
    public static final String TAG="MyObserver";
    @Override
    public void onSubscribe(Disposable d) {
        Log.i(TAG, "onSubscribe: ");
    }

    @Override
    public void onNext(Integer o) {
        Log.i(TAG, "onNext:+ "+o);
    }

    @Override
    public void onError(Throwable e) {
        Log.i(TAG, "onError: ");
    }

    @Override
    public void onComplete() {
        Log.i(TAG, "onComplete: ");
    }
}
