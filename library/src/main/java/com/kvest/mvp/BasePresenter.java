package com.kvest.mvp;

import android.os.Parcelable;
import android.support.annotation.CallSuper;

/**
 * Created by roman on 8/17/16.
 */
public abstract class BasePresenter<V extends BaseView, T extends Parcelable> {
    protected V view;

    @CallSuper
    public void attachView(V view){
        this.view = view;
    }

    @CallSuper
    public void detachView(){
        this.view = null;
    }

    public void onStart(){}

    public void onStop(){}

    public T getInstanceState(){
        return null;
    }

    public void restoreInstanceState(T savedInstanceState){}
}
