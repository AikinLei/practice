package com.Leon.rxLifeTest;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;

/**
 * @author leixinqiao
 * @create on 2019-11-22 12:12
 */
public class LifeOwnertest implements LifecycleObserver {

    public LifeOwnertest(LifecycleOwner owner){
        owner.getLifecycle().addObserver(this);

    }



}
