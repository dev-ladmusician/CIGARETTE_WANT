package com.gaincigarretprice.idiot.sun.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gaincigarretprice.idiot.sun.R;
import com.gaincigarretprice.idiot.sun.event.Events;
import com.gaincigarretprice.idiot.sun.model.data.Alarm;
import com.gaincigarretprice.idiot.sun.model.data.realm.AlarmObject;
import com.gaincigarretprice.idiot.sun.presenter.AlarmPresenter;
import com.gaincigarretprice.idiot.sun.util.Constant;
import com.gaincigarretprice.idiot.sun.view.adapter.AlarmAdapter;
import com.gaincigarretprice.idiot.sun.view.adapter.AlarmAdapterDataView;
import com.gaincigarretprice.idiot.sun.view.base.BaseActivity;
import com.gaincigarretprice.idiot.sun.view.dagger.DaggerMainComponent;
import com.gaincigarretprice.idiot.sun.view.dagger.MainModule;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;
import rx.Subscriber;

public class ActivityMain extends BaseActivity implements AlarmPresenter.View {
    private static final String TAG = "ACTIVITY_MAIN";

    @Bind(R.id.main_alarm_container)
    RecyclerView mainAlarmContainer;

    @Inject
    AlarmAdapterDataView mAlarmAdapterDataView;
    @Inject
    AlarmPresenter mAlarmPresenter;

    AlarmAdapter mAlarmAdapter;
    private Subscriber<RealmResults<AlarmObject>> subscriber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAlarmAdapter = new AlarmAdapter();

        DaggerMainComponent.builder()
                .mainModule(new MainModule(this, mAlarmAdapter))
                .build()
                .inject(this);

        subscriber = new Subscriber<RealmResults<AlarmObject>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(RealmResults<AlarmObject> alarmObjects) {
                mAlarmAdapter.setData(alarmObjects);
                mAlarmAdapter.refresh();
            }
        };
        Events.realmResultsPublishSubject.subscribe(subscriber);

        init();
    }

    @Override
    protected void onDestroy() {
        mAlarmPresenter.closeRealm();
        ButterKnife.unbind(this);
        subscriber.unsubscribe();
        super.onDestroy();
    }

    public void init() {
        mainAlarmContainer.setLayoutManager(new LinearLayoutManager(ActivityMain.this));

        mAlarmAdapter.setOnItemClickListener((adapter, position) -> {
            mAlarmPresenter.onItemClick(position);
        });
        mainAlarmContainer.setAdapter(mAlarmAdapter);

        mAlarmPresenter.loadItems();
    }

    @Override
    public void refresh() {
        mAlarmAdapterDataView.refresh();
    }

    @Override
    public void showAlarmInfo(int alarmId) {
        // Fragment? Activity?
        // 해당 알람 클릭 후 정보 보여주거나. 수정하는 화면.
        Intent intent = new Intent(this, ActivityAddAlarm.class);
        intent.putExtra(Alarm.ALARM_ID, alarmId);
        startActivity(intent);
    }

    @OnClick(R.id.fab)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(this, ActivityAddAlarm.class);
                startActivityForResult(intent, Constant.ADD_ALARM_REQUEST_CODE);
                break;
            default:
                break;
        }
    }
}
