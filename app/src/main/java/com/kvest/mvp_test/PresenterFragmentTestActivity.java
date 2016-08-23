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
    public static void start(Context context) {
        Intent intent = new Intent(context, PresenterFragmentTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presenter_fragment_test_activity);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.first_counter, CounterFragment.newInstance());
            transaction.add(R.id.second_counter, CounterFragment.newInstance());
            transaction.commit();
        }
    }
}