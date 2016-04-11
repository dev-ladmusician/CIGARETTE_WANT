package com.gaincigarretprice.idiot.sun.view.dagger;

import android.app.Activity;

import com.gaincigarretprice.idiot.sun.presenter.BasePresenter;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by jihoon on 2016. 4. 10..
 */
@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity activity() {
        return activity;
    }

    @Provides
    BasePresenter provideBasePresenter(BasePresenter presenter) {
        return presenter;
    }

    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

}

