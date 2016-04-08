package com.gaincigarretprice.idiot.sun.view.dagger;

import com.gaincigarretprice.idiot.sun.model.remote.dagger.NetworkModule;
import com.gaincigarretprice.idiot.sun.presenter.AlarmPresenter;
import com.gaincigarretprice.idiot.sun.presenter.AlarmPresenterImpl;
import com.gaincigarretprice.idiot.sun.view.adapter.AlarmAdapter;
import com.gaincigarretprice.idiot.sun.view.adapter.AlarmAdapterDataModel;
import com.gaincigarretprice.idiot.sun.view.adapter.AlarmAdapterDataView;

import dagger.Module;
import dagger.Provides;

@Module(includes = NetworkModule.class)
public class MainModule {
    private AlarmPresenter.View mView;
    private AlarmAdapter mAlarmAdapter;

    public MainModule(AlarmPresenter.View mView, AlarmAdapter mAlarmAdapter) {
        this.mView = mView;
        this.mAlarmAdapter = mAlarmAdapter;
    }

    @Provides
    AlarmAdapterDataModel provideAlarmAdapterDataModel() {
        return mAlarmAdapter;
    }

    @Provides
    AlarmAdapterDataView provideAlarmAdapterDataView() {
        return mAlarmAdapter;
    }

    @Provides
    AlarmPresenter provideAlarmPresenter(AlarmPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    AlarmPresenter.View provideView() {
        return mView;
    }

}
