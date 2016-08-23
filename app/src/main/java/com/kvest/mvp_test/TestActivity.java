package com.kvest.mvp_test;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kvest.mvp_test.databinding.TestActivityBinding;

/**
 * Created by roman on 8/16/16.
 */
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TestActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.test_activity);
        binding.setViewModel(new ViewModel());
    }

    public class ViewModel {
        public void showPresenterActivityTest(Context context) {
            PresenterActivityTest.start(context);
        }
    }
}
