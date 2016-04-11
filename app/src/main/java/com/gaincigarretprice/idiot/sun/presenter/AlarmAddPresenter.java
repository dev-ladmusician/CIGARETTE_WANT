package com.gaincigarretprice.idiot.sun.presenter;

import com.gaincigarretprice.idiot.sun.model.data.dto.AlarmDTO;

/**
 * Created by jihoon on 2016. 4. 9..
 */
public interface AlarmAddPresenter extends BasePresenter{

    int saveAlarmToRealmAndGetAlarmId(AlarmDTO alarmDTO);

    @Override
    void closeRealm();
}
