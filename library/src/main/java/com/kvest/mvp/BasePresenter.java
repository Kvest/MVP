package com.kvest.mvp;

import android.support.annotation.CallSuper;

/**
 * Created by roman on 8/17/16.
 */
public abstract class BasePresenter<V extends BaseView> {
    protected V view;
    private boolean viewAttached;

    public BasePresenter() {
        view = NullObjectUtils.create(getViewClass());
        viewAttached = false;
    }

    @CallSuper
    public void onAttachView(V view){
        this.view = view;
        this.viewAttached = true;
    }

    @CallSuper
    public void onDetachView(){
        this.view = NullObjectUtils.create(getViewClass());
        this.viewAttached = false;
    }

    /**
     * Check if a real view is attached to this presenter
     * @return true if a real view is attached to this presenter, false if a null-object is used as a view
     */
    public boolean isViewAttached() {
        return viewAttached;
    }

    public abstract Class<V> getViewClass();

    public void onStart(){}

    public void onStop(){}

    public void onDestroy(){}
}
