package com.kvest.mvp_test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.kvest.mvp.BaseActivity;

/**
 * Created by roman on 8/23/16.
 */
public class PresenterFragmentTestActivity extends BaseActivity {
    private static final String EXTRA_RETAIN_FIRST = "com.kvest.mvp_test.extra.RETAIN_FIRST ";
    private static final String EXTRA_RETAIN_SECOND = "com.kvest.mvp_test.extra.RETAIN_SECOND";

    public static void start(Context context, boolean retainFirst, boolean retainSecond) {
        Intent intent = new Intent(context, PresenterFragmentTestActivity.class);

        intent.putExtra(EXTRA_RETAIN_FIRST, retainFirst);
        intent.putExtra(EXTRA_RETAIN_SECOND, retainSecond);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presenter_fragment_test_activity);

        if (savedInstanceState == null) {
            CounterFragment first = CounterFragment.newInstance();
            first.setRetain(getIntent().getBooleanExtra(EXTRA_RETAIN_FIRST, true));
            CounterFragment second = CounterFragment.newInstance();
            second.setRetain(getIntent().getBooleanExtra(EXTRA_RETAIN_SECOND, true));

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.first_counter, first);
            transaction.add(R.id.second_counter, second);
            transaction.commit();
        }
    }
}