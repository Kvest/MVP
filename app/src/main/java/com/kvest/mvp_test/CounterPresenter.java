package com.kvest.mvp_test;

import android.util.Log;

/**
 * Created by roman on 8/23/16.
 */
public class CounterPresenter extends CounterContract.Presenter {
    private int counter;

    public CounterPresenter() {
        counter = 0;

        log("Created new CounterPresenter");
    }

    @Override
    public void increment() {
        counter++;
        updateViewCounter();
    }

    @Override
    public void decrement() {
        counter--;
        updateViewCounter();
    }

    private void updateViewCounter() {
        view.setCounterValue(counter);
    }

    @Override
    public void onAttachView(CounterContract.View view) {
        super.onAttachView(view);

        log("onAttachView");

        view.setCounterValue(counter);
    }

    @Override
    public void onDetachView() {
        super.onDetachView();

        log("onDetachView");
    }

    @Override
    public void onStart() {
        super.onStart();

        log("onStart");
    }

    @Override
    public void onStop() {
        super.onStop();

        log("onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        log("onDestroy");
    }

    @Override
    public Class<CounterContract.View> getViewClass() {
        return CounterContract.View.class;
    }

    private void log(String message) {
        view.logMessage(message);

        Log.d("KVEST_TAG", message);
    }
}
