package com.kvest.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

/**
 * Created by roman on 8/17/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements PresenterPersistStorage {
    private SimpleArrayMap<UUID, Presenter> presentersContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        restoreOrCreatePresentersContainer();

        super.onCreate(savedInstanceState);
    }

    @Override
    public Presenter getPresenter(UUID uuid) {
        return presentersContainer.get(uuid);
    }

    @Override
    public UUID addPresenter(Presenter presenter) {
        final UUID uuid = UUID.randomUUID();
        presentersContainer.put(uuid, presenter);
        return uuid;
    }

    @Override
    public Presenter removePresenter(UUID uuid) {
        return presentersContainer.remove(uuid);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        Object custom = onRetainCustomNonConfigurationObject();

        NonConfigurationInstance nci = new NonConfigurationInstance(custom, presentersContainer);
        return nci;
    }

    /**
     * Use this instead of {@link #onRetainCustomNonConfigurationInstance()}}.
     * Retrieve later with {@link #getLastCustomNonConfigurationObject()}.
     */
    public Object onRetainCustomNonConfigurationObject() {
        return null;
    }

    /**
     * @return value previously returned from
     *         {@link #onRetainCustomNonConfigurationObject()}
     */
    public Object getLastCustomNonConfigurationObject() {
        NonConfigurationInstance nci = (NonConfigurationInstance)getLastCustomNonConfigurationInstance();
        return nci != null ? nci.custom : null;
    }

    private void restoreOrCreatePresentersContainer() {
        NonConfigurationInstance nci = (NonConfigurationInstance)getLastCustomNonConfigurationInstance();
        presentersContainer = (nci == null) ? new SimpleArrayMap<UUID, Presenter>() : nci.presentersContainer;
    }

    private static final class NonConfigurationInstance {
        private final Object custom;
        private final SimpleArrayMap<UUID, Presenter> presentersContainer;

        public NonConfigurationInstance(Object custom, SimpleArrayMap<UUID, Presenter> presentersContainer) {
            this.custom = custom;
            this.presentersContainer = presentersContainer;
        }
    }
}
