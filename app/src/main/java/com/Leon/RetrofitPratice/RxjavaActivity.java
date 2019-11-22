package com.Leon.RetrofitPratice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class RxjavaActivity extends AppCompatActivity {

    public static final String TAG = "RxJava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);


        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("握草  很无语");
                emitter.onNext("又来搞事情");
                emitter.onNext("匿名");
                emitter.onComplete();
            }
        });

        Observable observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("握草  很无语");
                emitter.onNext("又来搞事情");
                emitter.onNext("匿名");
                emitter.onComplete();
            }
        });

        Disposable subscribe = Observable.timer(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.i(TAG, "accept: " + aLong);
            }
        });




    }
}
