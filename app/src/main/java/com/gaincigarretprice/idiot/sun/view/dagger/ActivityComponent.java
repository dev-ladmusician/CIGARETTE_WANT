package com.gaincigarretprice.idiot.sun.view.dagger;

import android.app.Activity;

import dagger.Component;

/**
 * Created by jihoon on 2016. 4. 10..
 */
@Component(
        modules = ActivityModule.class
)
public interface ActivityComponent {
    public void inject(Activity activity);
}
