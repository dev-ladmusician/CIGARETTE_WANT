package com.gaincigarretprice.idiot.sun.presenter;

import com.gaincigarretprice.idiot.sun.model.data.dto.AlarmDTO;
import com.gaincigarretprice.idiot.sun.model.data.realm.AlarmObject;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by jihoon on 2016. 4. 10..
 */
public class AlarmAddPresenterImpl implements AlarmAddPresenter {

    private static final String TAG = AlarmAddPresenterImpl.class.getSimpleName();

    Realm realm;

    @Inject
    public AlarmAddPresenterImpl(Realm realm) {
        this.realm = realm;
    }

    public AlarmObject getEditTargetAlarmObject(int alarmId) {

        RealmResults<AlarmObject> realmResults =
                realm.where(AlarmObject.class).equalTo("_alarmid", alarmId).findAll();

        return realmResults.size() > 0 ? realmResults.get(0) : null;

    }

    @Override
    public int saveAlarmToRealmAndGetAlarmId(AlarmDTO alarmDTO) {
        try {

            realm.beginTransaction();


            // TODO: 2016. 4. 11. alarmObject에 data bind method가 필요..  파라미터를 AlarmDTO로 ..?

            realm.commitTransaction();

            return alarmDTO.get_alarmid();

        } catch (NullPointerException e) {

            return -1;
        }
    }

    public AlarmObject createAlarmToRealmAndGetAlarm(AlarmDTO alarmDTO) {

        int maxId = getLastPrimaryKey();

        if (maxId == 0) {

            realm.beginTransaction();

            AlarmObject alarmObject = realm.createObject(AlarmObject.class);
            alarmObject.setHour(alarmDTO.getHour());
            alarmObject.setMin(alarmDTO.getMin());
            alarmObject.setSun(alarmDTO.isSun());
            alarmObject.setMon(alarmDTO.isMon());
            alarmObject.setTue(alarmDTO.isTue());
            alarmObject.setWed(alarmDTO.isWed());
            alarmObject.setThur(alarmDTO.isThur());
            alarmObject.setFri(alarmDTO.isFri());
            alarmObject.setSat(alarmDTO.isSat());

            realm.commitTransaction();
            return alarmObject;

        } else {

            return realm.where(AlarmObject.class).equalTo("_alarmid", alarmDTO.get_alarmid()).findAll().get(0);
        }

    }

    private int getLastPrimaryKey() {
        try {
            return realm.allObjects(AlarmObject.class).max("_alarmid").intValue();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    @Override
    public void closeRealm() {
        realm.close();
    }
}
