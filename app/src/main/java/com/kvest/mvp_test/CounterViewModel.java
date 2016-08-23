package com.kvest.mvp_test;

import android.databinding.ObservableInt;

/**
 * Created by roman on 8/23/16.
 */
public class CounterViewModel {
    private final CounterContract.Presenter presenter;
    private final ObservableInt counter;

    public CounterViewModel(CounterContract.Presenter presenter) {
        this.presenter = presenter;
        this.counter = new ObservableInt(0);
    }

    public ObservableInt getCounter() {
        return counter;
    }

    public void increment() {
        presenter.increment();
    }

    public void decrement() {
        presenter.decrement();
    }
}
