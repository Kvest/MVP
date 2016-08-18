package com.kvest.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by roman on 8/17/16.
 */
public abstract class PresenterFragment<T extends Presenter> extends Fragment implements View {
    private static final String KEY_PRESENTER_UUID = "com.kvest.mvp.key.PRESENTER_UUID";

    protected T presenter;
    private UUID presenterUUID;
    private PresenterPersistStorage presenterPersistStorage;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            presenterPersistStorage = (PresenterPersistStorage)context;
        } catch (ClassCastException cce) {
            throw new RuntimeException("PresenterFragment must be used with Activity that implements PresenterPersistStorage");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        restoreOrCreatePresenter(savedInstanceState);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
    public void onDestroyView() {
        super.onDestroyView();

        if (presenter != null) {
            presenter.onDetachView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (!retainPresenter() || !getActivity().isChangingConfigurations()) {
            if (presenter != null) {
                presenterPersistStorage.removePresenter(presenterUUID);
                presenter.onDestroy();
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenterPersistStorage = null;
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
            presenter = (T)presenterPersistStorage.getPresenter(presenterUUID);

            if (presenter != null) {
                onPresenterRestored(presenter);
            }
        }

        if (presenter == null) {
            //need to create new presenter
            presenter = createPresenter();
            presenterUUID = presenterPersistStorage.addPresenter(presenter);
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

    @NonNull
    protected abstract T createPresenter();
}
