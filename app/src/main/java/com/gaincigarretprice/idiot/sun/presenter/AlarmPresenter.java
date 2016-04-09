package com.gaincigarretprice.idiot.sun.presenter;

/**
 * Created by ladmusician on 4/5/16.
 */
public interface AlarmPresenter {
    void loadItems();
    void onItemClick(int position);
    void deleteItem(int position);
    void closeRealm();

    interface View {
        void refresh();
        void showAlarmInfo(int alarmId);
    }
}
