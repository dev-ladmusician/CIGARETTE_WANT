package com.gaincigarretprice.idiot.sun.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gaincigarretprice.idiot.sun.R;
import com.gaincigarretprice.idiot.sun.presenter.AlarmPresenter;
import com.gaincigarretprice.idiot.sun.util.LogUtil;
import com.gaincigarretprice.idiot.sun.view.adapter.AlarmAdapter;
import com.gaincigarretprice.idiot.sun.view.adapter.AlarmAdapterDataView;
import com.gaincigarretprice.idiot.sun.view.base.BaseActivity;
import com.gaincigarretprice.idiot.sun.view.dagger.DaggerMainComponent;
import com.gaincigarretprice.idiot.sun.view.dagger.MainModule;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityMain extends BaseActivity implements AlarmPresenter.View {
    private static final String TAG = "ACTIVITY_MAIN";
    private Context mContext = null;

    @Bind(R.id.main_alarm_container)
    RecyclerView mainAlarmContainer;

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

    @OnClick(R.id.fab)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                LogUtil.print(TAG, "CLICK FAB");
                break;
            default:
                break;
        }
    }
}
