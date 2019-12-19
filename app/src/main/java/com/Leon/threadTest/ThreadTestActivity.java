package com.Leon.threadTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.Leon.RetrofitPratice.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class ThreadTestActivity extends AppCompatActivity {
    public static final String TAG = "ThreadTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);


        Observable.interval(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new MyObserver());



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

//        Observable.interval(5, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.i(TAG, "onSubscribe: inner ");
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        Log.i(TAG, "onNext: inner"+aLong);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.i(TAG, "onComplete: inner");
//                    }
//                });
    }
}
