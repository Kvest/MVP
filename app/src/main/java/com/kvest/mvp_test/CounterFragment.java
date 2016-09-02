package com.kvest.mvp_test;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kvest.mvp.PresenterFragment;
import com.kvest.mvp_test.databinding.CounterFragmentBinding;

/**
 * Created by roman on 8/23/16.
 */
public class CounterFragment extends PresenterFragment<CounterContract.Presenter> implements CounterContract.View {
    private static final String KEY_RETAIN = "com.kvest.mvp_test.key.RETAIN";

    private CounterViewModel counterViewModel;
    private boolean retain = true;

    public static CounterFragment newInstance() {
        return new CounterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            retain = savedInstanceState.getBoolean(KEY_RETAIN, retain);
        }

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        counterViewModel = new CounterViewModel(presenter);

        CounterFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.counter_fragment, container, false);
        binding.setCounterViewModel(counterViewModel);
        return binding.getRoot();
    }

    @Override
    protected boolean retainPresenter() {
        return retain;
    }

    @NonNull
    @Override
    protected CounterContract.Presenter createPresenter() {
        return new CounterPresenter();
    }

    @Override
    public void setCounterValue(int value) {
        counterViewModel.getCounter().set(value);
    }

    @Override
    public void logMessage(String message) {
        log("Presenter", message);
    }

    public void setRetain(boolean retain) {
        this.retain = retain;
    }

    private void log(String component, String message) {
        Log.d("KVEST_TAG", String.format("[%s] %s", component, message));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(KEY_RETAIN, retain);
    }
}
