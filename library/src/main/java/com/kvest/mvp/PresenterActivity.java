package com.kvest.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;

/**
 * Created by roman on 8/17/16.
 */
public abstract class PresenterActivity<T extends BasePresenter> extends BaseActivity implements BaseView {
    private static final String KEY_PRESENTER_UUID = "com.kvest.mvp.key.PRESENTER_UUID";

    protected T presenter;
    private UUID presenterUUID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        restoreOrCreatePresenter(savedInstanceState);

        onBeforeAttachView();

        if (presenter != null) {
            presenter.onAttachView(this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (presenter != null) {
            presenter.onDetachView();
        }

        if (!retainPresenter() || isFinishing()) {
            if (presenter != null) {
                removePresenter(presenterUUID);
                presenter.onDestroy();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_PRESENTER_UUID, presenterUUID);
        super.onSaveInstanceState(outState);
    }

    private void restoreOrCreatePresenter(@Nullable Bundle savedInstanceState)  {
        if (savedInstanceState != null) {
            //try to retrieve presenter from persist storage
            presenterUUID = (UUID) savedInstanceState.getSerializable(KEY_PRESENTER_UUID);
            presenter = (T)getPresenter(presenterUUID);

            if (presenter != null) {
                onPresenterRestored(presenter);
            }
        }

        if (presenter == null) {
            //need to create new presenter
            presenter = createPresenter();
            presenterUUID = addPresenter(presenter);
        }
    }

    /**
     * Indicates if presenter should be kept or not.
     * @return true if presenter should be retained, false otherwise.
     *         Default value is true
     */
    protected boolean retainPresenter() {
        return true;
    }

    /**
     * Called after presenter restored. Called before {@link #onCreate(android.os.Bundle)}.
     * View is not attached to presenter at this moment.
     */
    protected void onPresenterRestored(T presenter) { }

    /*
    Method is called when all the stuff in OnCreate is finished and before the view will be attached to the presenter
    This method is handy for doing UI initializations
     */
    protected void onBeforeAttachView() {}

    @NonNull
    protected abstract T createPresenter();
}
