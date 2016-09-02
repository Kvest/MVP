package com.kvest.mvp_test;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.kvest.mvp.PresenterActivity;
import com.kvest.mvp_test.databinding.PresenterActivityTestBinding;

/**
 * Created by roman on 8/23/16.
 */
public class PresenterActivityTest extends PresenterActivity<CounterContract.Presenter> implements CounterContract.View {
    private static final String EXTRA_RETAIN = "com.kvest.mvp_test.extra.RETAIN";

    private TextView log;
    private CounterViewModel counterViewModel;
    private boolean retain = true;
    private StringBuilder logCache = new StringBuilder();

    public static void start(Context context, boolean retain) {
        Intent intent = new Intent(context, PresenterActivityTest.class);
        intent.putExtra(EXTRA_RETAIN, retain);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        retain = getIntent().getBooleanExtra(EXTRA_RETAIN, retain);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onBeforeAttachView() {
        log("onBeforeAttachView");

        PresenterActivityTestBinding binding = DataBindingUtil.setContentView(this, R.layout.presenter_activity_test);
        counterViewModel = new CounterViewModel(presenter);
        binding.setCounterViewModel(counterViewModel);

        log = binding.log;
        log.setText(logCache.toString());
    }

    @Override
    protected void onPresenterRestored(CounterContract.Presenter presenter) {
        super.onPresenterRestored(presenter);

        log("onPresenterRestored");
    }

    @Override
    protected boolean retainPresenter() {
        return retain;
    }

    @NonNull
    @Override
    protected CounterContract.Presenter createPresenter() {
        log("createPresenter");

        return new CounterPresenter();
    }

    @Override
    public void setCounterValue(int value) {
        counterViewModel.getCounter().set(value);
    }

    @Override
    public void logMessage(String message) {
        log("View", message);
    }

    private void log(String component, String message) {
        if (log == null) {
            logCache.append(String.format("[%s] %s\n", component, message));
        } else {
            log.append(String.format("[%s] %s\n", component, message));
        }
    }

    private void log(String message) {
        Log.d("KVEST_TAG", message);

        log("PresenterActivity", message);
    }
}
