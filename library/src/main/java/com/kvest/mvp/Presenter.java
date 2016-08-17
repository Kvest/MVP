package com.kvest.mvp;

import android.os.Parcelable;
import android.support.annotation.CallSuper;

/**
 * Created by roman on 8/17/16.
 */
public abstract class Presenter<V extends View> {
    protected V view;

    @CallSuper
    public void onAttachView(V view){
        this.view = view;
    }

    @CallSuper
    public void onDetachView(){
        this.view = null;
    }

    public void onStart(){}

    public void onStop(){}

    public void onDestroy(){}
}
