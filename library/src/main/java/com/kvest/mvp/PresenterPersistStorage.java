package com.kvest.mvp;

import java.util.UUID;

/**
 * Created by roman on 8/17/16.
 */
public interface PresenterPersistStorage {
    Presenter getPresenter(UUID uuid);
    UUID addPresenter(Presenter presenter);
    Presenter removePresenter(UUID uuid);
}
