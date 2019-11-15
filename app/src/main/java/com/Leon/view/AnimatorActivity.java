package com.Leon.view;

import android.animation.ObjectAnimator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.Leon.RetrofitPratice.R;
public class AnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        CircleView circleView = (CircleView) findViewById(R.id.circle_view);



        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(circleView, "mRadius",  UiUtil.dp2px(300));
        objectAnimator.setStartDelay(2000);
        objectAnimator.start();

    }
}
