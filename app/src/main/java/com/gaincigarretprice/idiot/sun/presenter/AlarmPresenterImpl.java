package com.gaincigarretprice.idiot.sun.presenter;

import javax.inject.Inject;

/**
 * Created by ladmusician on 4/5/16.
 */
public class AlarmPresenterImpl implements AlarmPresenter {
    private View mView;

    @Inject
    public AlarmPresenterImpl(View view) {
        this.mView = view;
    }


    @Override
    public void loadItems() {

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void deleteItem(int position) {

    }
}
