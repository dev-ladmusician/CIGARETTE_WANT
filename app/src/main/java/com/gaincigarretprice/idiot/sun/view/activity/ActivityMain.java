package com.gaincigarretprice.idiot.sun.view.activity;

import android.content.Context;
import android.os.Bundle;

import com.gaincigarretprice.idiot.sun.R;
import com.gaincigarretprice.idiot.sun.presenter.AlarmPresenter;
import com.gaincigarretprice.idiot.sun.view.adapter.AlarmAdapter;
import com.gaincigarretprice.idiot.sun.view.adapter.AlarmAdapterDataView;
import com.gaincigarretprice.idiot.sun.view.base.BaseActivity;
import com.gaincigarretprice.idiot.sun.view.dagger.DaggerMainComponent;
import com.gaincigarretprice.idiot.sun.view.dagger.MainModule;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class ActivityMain extends BaseActivity implements AlarmPresenter.View {
    private static final String TAG = "ACTIVITY_MAIN";
    private Context mContext = null;

    @Inject
    AlarmAdapterDataView mAlarmAdapterDataView;
    @Inject
    AlarmPresenter mAlarmPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mContext = getApplicationContext();
        AlarmAdapter adapter = new AlarmAdapter(mContext);
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this, adapter))
                .build()
                .inject(this);

        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void init() {


    }

    @Override
    public void refresh() {
        mAlarmAdapterDataView.refresh();
    }
}
