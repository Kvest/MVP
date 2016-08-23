package com.kvest.mvp_test;

import android.os.Bundle;

import com.kvest.mvp.BasePresenter;
import com.kvest.mvp.BaseView;

/**
 * Created by roman on 8/23/16.
 */
public class CounterContract {
    interface View extends BaseView {
        void setCounterValue(int value);
        void logMessage(String message);
    }

    abstract static class Presenter extends BasePresenter<View> {
        abstract void increment();
        abstract void decrement();
    }
}
