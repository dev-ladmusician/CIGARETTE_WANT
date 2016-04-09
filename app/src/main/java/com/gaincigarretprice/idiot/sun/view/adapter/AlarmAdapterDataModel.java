package com.gaincigarretprice.idiot.sun.view.adapter;

import com.gaincigarretprice.idiot.sun.model.data.dto.AlarmDTO;
import com.gaincigarretprice.idiot.sun.model.data.realm.AlarmObject;

/**
 * Created by ladmusician on 4/5/16.
 */
public interface AlarmAdapterDataModel {
    void addItem(AlarmDTO item);
    void deleteItem(int position);
    int getSize();
    AlarmObject getItem(int position);
}
