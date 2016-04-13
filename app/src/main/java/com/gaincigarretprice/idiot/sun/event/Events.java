package com.gaincigarretprice.idiot.sun.event;

import com.gaincigarretprice.idiot.sun.model.data.realm.AlarmObject;

import io.realm.RealmResults;
import rx.subjects.PublishSubject;

/**
 * Created by jihoon on 2016. 4. 9..
 */
public class Events {

    public static PublishSubject<RealmResults<AlarmObject>> realmResultsPublishSubject = PublishSubject.create();
}
