package com.gaincigarretprice.idiot.sun.view.adapter;

import com.gaincigarretprice.idiot.sun.view.interfaces.OnItemClickListener;

/**
 * Created by ladmusician on 4/5/16.
 */
public interface AlarmAdapterDataView {
    void refresh();
    void setOnChangeItemStateListener();
    void setOnItemClickListener(OnItemClickListener onRecyclerItemClickListener);
}

/*
public interface ImageAdapterDataView {
    void refresh();

    void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener);
}
*/

