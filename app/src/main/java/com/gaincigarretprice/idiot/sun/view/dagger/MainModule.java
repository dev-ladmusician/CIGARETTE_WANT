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

    public MainModule(AlarmPresenter.View mView) {
        this.mView = mView;
    }

    @Provides
    AlarmAdapterDataModel provideAlarmAdapterDataModel(AlarmAdapterDataModel mAdapter) {
        return mAdapter;
    }

    @Provides
    AlarmAdapterDataView provideAlarmAdapterDataView(AlarmAdapterDataView mAdapter) {
        return mAdapter;
    }

    @Provides
    AlarmPresenter provideAlarmPresenter(AlarmPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    AlarmPresenter.View provideView() {
        return mView;
    }

    @Provides
    AlarmAdapter provideAlarmAdapter(AlarmAdapter mAdapter) { return mAdapter; }
}
