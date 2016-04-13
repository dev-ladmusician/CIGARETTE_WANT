package com.gaincigarretprice.idiot.sun.app;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ladmusician on 4/7/16.
 */
public class CigarreteApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        initRealmConfiguration();
    }

    private void initRealmConfiguration() {
        RealmConfiguration myConfig = new RealmConfiguration.Builder(this)
                .build();
        Realm.setDefaultConfiguration(myConfig);
    }
}
