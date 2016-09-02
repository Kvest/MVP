package com.kvest.mvp_test;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by roman on 9/2/16.
 */
public class TestApplication extends Application {
    private static TestApplication application;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        refWatcher = LeakCanary.install(this);

        application = this;
    }

    public static TestApplication getApplication() {
        return application;
    }

    public RefWatcher getRefWatcher() {
        return refWatcher;
    }
}
