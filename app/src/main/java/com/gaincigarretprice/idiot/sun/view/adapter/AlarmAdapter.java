package com.gaincigarretprice.idiot.sun.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.gaincigarretprice.idiot.sun.model.data.Alarm;
import com.gaincigarretprice.idiot.sun.view.interfaces.OnItemClickListener;
import com.gaincigarretprice.idiot.sun.view.interfaces.OnItemStateChangeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by ladmusician on 4/5/16.
 */
public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>
    implements AlarmAdapterDataModel, AlarmAdapterDataView {
    private Context mContext = null;
    private List<Alarm> mAlarmList = null;
    private OnItemStateChangeListener mItemStateChangeListener = null;
    private OnItemClickListener mItemClickListener = null;

    public AlarmAdapter(Context mContext) {
        this.mContext = mContext;
        this.mAlarmList = new ArrayList<>();
    }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return getSize();
    }

    @Override
    public void addItem(Alarm item) {
        mAlarmList.add(item);
    }

    @Override
    public void deleteItem(int position) {
        mAlarmList.remove(position);
    }

    @Override
    public int getSize() {
        return mAlarmList.size();
    }

    @Override
    public Alarm getItem(int position) {
        return mAlarmList.get(position);
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnChangeItemStateListener() {

    }

    @Override
    public void setOnItemClickListener() {

    }

    public void setItemStateChangeListener(OnItemStateChangeListener mItemStateChangeListener) {
        this.mItemStateChangeListener = mItemStateChangeListener;
    }

    public void setItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    class AlarmViewHolder extends RecyclerView.ViewHolder {
        public AlarmViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
