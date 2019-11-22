package com.Leon.rxLifeTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.Leon.RetrofitPratice.R;
import com.rxjava.rxlife.RxConverter;
import com.rxjava.rxlife.RxLife;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class RxLifeTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_life_test);

        RxConverter<Object> as = RxLife.as(this);

        Observable.timer(5, TimeUnit.SECONDS)
                .as(RxLife.as(this))     //此时的this Activity/Fragment对象
                .subscribe(aLong -> {
                    Log.e("LJX", "accept =" + aLong);
                });

    }
}
