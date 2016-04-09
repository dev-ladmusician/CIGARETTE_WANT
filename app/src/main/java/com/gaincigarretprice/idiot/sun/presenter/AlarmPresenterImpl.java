package com.gaincigarretprice.idiot.sun.presenter;

import com.gaincigarretprice.idiot.sun.event.Events;
import com.gaincigarretprice.idiot.sun.model.data.realm.AlarmObject;
import com.gaincigarretprice.idiot.sun.model.remote.AlarmService;
import com.gaincigarretprice.idiot.sun.util.LogUtil;
import com.gaincigarretprice.idiot.sun.view.adapter.AlarmAdapterDataModel;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Subscriber;

/**
 * Created by ladmusician on 4/5/16.
 */
public class AlarmPresenterImpl implements AlarmPresenter {
    private View mView;
    private AlarmService mAlarmService;
    private AlarmAdapterDataModel mAlarmAdapterDataModel;
    private final static int USER_ID = 1;

    private int instanceCount =0;
    private Realm realm;

    @Inject
    public AlarmPresenterImpl(View view, AlarmService service, AlarmAdapterDataModel model) {
        this.mView = view;
        this.mAlarmService = service;
        this.mAlarmAdapterDataModel = model;
        LogUtil.print("AlarmPresenterImpl Constructor Called Count", ++instanceCount + "");
    }


    @Override
    public void loadItems() {
//        mAlarmService.getAlarmsById(USER_ID)
//                .subscribeOn(Schedulers.newThread())
//                .filter(item -> item.getCode() == 200 && item.getResult() != null)
//                .map(ResultDTO::getResult)
//                .filter(rtv -> rtv != null)
//                .flatMap(alarmResult -> Observable.from(alarmResult))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(mAlarmAdapterDataModel::addItem,
//                        Throwable::printStackTrace,
//                        mView::refresh);
        realm = Realm.getDefaultInstance();
        realm.where(AlarmObject.class).findAllAsync()
                .asObservable()
                .subscribe(new Subscriber<RealmResults<AlarmObject>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.print(AlarmPresenterImpl.class.getSimpleName(), e.toString());
                    }

                    @Override
                    public void onNext(RealmResults<AlarmObject> alarmObjects) {
                        LogUtil.print(AlarmPresenterImpl.class.getSimpleName(), alarmObjects.size()+"");
                        Events.realmResultsPublishSubject.onNext(alarmObjects);
                    }
                });

    }

    @Override
    public void onItemClick(int position) {
        showAlarmInfo(getAlarmId(position));
    }

    private int getAlarmId(int position) {
        AlarmObject alarmObject = mAlarmAdapterDataModel.getItem(position);
        return alarmObject.get_alarmid();
    }

    private void showAlarmInfo(int alarmId) {
        mView.showAlarmInfo(alarmId);
    }

    @Override
    public void deleteItem(int position) {

    }

    @Override
    public void closeRealm() {
        realm.close();
    }

}
