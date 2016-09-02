package com.kvest.mvp_test;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.kvest.mvp.PresenterActivity;
import com.kvest.mvp_test.databinding.ViewPagerTestActivityBinding;

/**
 * Created by roman on 9/1/16.
 */
public class ViewPagerTestActivity extends PresenterActivity<CounterContract.Presenter> implements CounterContract.View {
    private CounterViewModel counterViewModel;

    public static void start(Context context) {
        Intent intent = new Intent(context, ViewPagerTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onBeforeAttachView() {
        ViewPagerTestActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.view_pager_test_activity);
        counterViewModel = new CounterViewModel(presenter);
        binding.setCounterViewModel(counterViewModel);

        binding.pager.setAdapter(new CountersPagerAdapter(getSupportFragmentManager()));
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
        Log.d("KVEST_TAG", message);
    }

    private static class CountersPagerAdapter extends FragmentStatePagerAdapter {
        public CountersPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public static final int PAGES_COUNT = 6;

        @Override
        public Fragment getItem(int position) {
            CounterFragment counterFragment = CounterFragment.newInstance();
            counterFragment.setRetain(position % 2 == 0);
            return counterFragment;
        }

        @Override
        public int getCount() {
            return PAGES_COUNT;
        }
    }
}
