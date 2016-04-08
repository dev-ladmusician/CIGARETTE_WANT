package com.gaincigarretprice.idiot.sun.presenter;

import com.gaincigarretprice.idiot.sun.model.data.dto.AlarmDTO;
import com.gaincigarretprice.idiot.sun.model.data.dto.ResultDTO;
import com.gaincigarretprice.idiot.sun.model.remote.AlarmService;
import com.gaincigarretprice.idiot.sun.view.adapter.AlarmAdapterDataModel;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ladmusician on 4/5/16.
 */
public class AlarmPresenterImpl implements AlarmPresenter {
    private View mView;
    private AlarmService mAlarmService;
    private AlarmAdapterDataModel mAlarmAdapterDataModel;
    private final static int USER_ID = 1;

    @Inject
    public AlarmPresenterImpl(View view, AlarmService service, AlarmAdapterDataModel model) {
        this.mView = view;
        this.mAlarmService = service;
        this.mAlarmAdapterDataModel = model;
    }


    @Override
    public void loadItems() {
        mAlarmService.getAlarmsById(USER_ID)
                .subscribeOn(Schedulers.newThread())
                .filter(item -> item.getCode() == 200 && item.getResult() != null)
                .map(ResultDTO::getResult)
                .filter(rtv -> rtv != null)
                .flatMap(alarmResult -> Observable.from(alarmResult))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mAlarmAdapterDataModel::addItem,
                        Throwable::printStackTrace,
                        mView::refresh);
    }

    @Override
    public void onItemClick(int position) {
        showAlarmInfo(getAlarmId(position));
    }

    private int getAlarmId(int position) {
        AlarmDTO alarmDTO = mAlarmAdapterDataModel.getItem(position);
        int alarmId = alarmDTO.get_alarmid();
        return alarmId;
    }

    private void showAlarmInfo(int alarmId) {
        mView.showAlarmInfo(alarmId);
    }

    @Override
    public void deleteItem(int position) {

    }
}
