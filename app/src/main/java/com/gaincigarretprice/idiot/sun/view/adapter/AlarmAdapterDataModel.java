package com.gaincigarretprice.idiot.sun.view.adapter;

import com.gaincigarretprice.idiot.sun.model.data.Alarm;

/**
 * Created by ladmusician on 4/5/16.
 */
public interface AlarmAdapterDataModel {
    void addItem(Alarm item);
    void deleteItem(int position);
    int getSize();
    Alarm getItem(int position);
}
