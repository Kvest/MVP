package com.kvest.mvp;

import java.util.UUID;

/**
 * Created by roman on 8/17/16.
 */
interface PresenterPersistStorage {
    BasePresenter getPresenter(UUID uuid);
    UUID addPresenter(BasePresenter presenter);
    BasePresenter removePresenter(UUID uuid);
}
