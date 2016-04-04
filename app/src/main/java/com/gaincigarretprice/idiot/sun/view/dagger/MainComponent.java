package com.gaincigarretprice.idiot.sun.view.dagger;

import com.gaincigarretprice.idiot.sun.view.activity.ActivityMain;

import dagger.Component;

@Component(modules = {MainModule.class})
public interface MainComponent {
    void inject(ActivityMain activityMain);
}
