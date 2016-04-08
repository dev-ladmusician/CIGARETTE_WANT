package com.gaincigarretprice.idiot.sun.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaincigarretprice.idiot.sun.R;
import com.gaincigarretprice.idiot.sun.model.data.dto.AlarmDTO;
import com.gaincigarretprice.idiot.sun.view.interfaces.OnItemClickListener;
import com.gaincigarretprice.idiot.sun.view.interfaces.OnItemStateChangeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ladmusician on 4/5/16.
 */
public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>
        implements AlarmAdapterDataModel, AlarmAdapterDataView {
    private List<AlarmDTO> mAlarmList = new ArrayList<>();
    private OnItemStateChangeListener mItemStateChangeListener = null;
    private OnItemClickListener mItemClickListener = null;

    public AlarmAdapter() {
    }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm, parent, false);
        return new AlarmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return getSize();
    }

    @Override
    public void addItem(AlarmDTO item) {
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
    public AlarmDTO getItem(int position) {
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
    public void setOnItemClickListener(OnItemClickListener onRecyclerItemClickListener) {

    }

    public void setItemStateChangeListener(OnItemStateChangeListener mItemStateChangeListener) {
        this.mItemStateChangeListener = mItemStateChangeListener;
    }

    public void setItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    class AlarmViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_alarm_time)
        TextView mTxtTime;

        public AlarmViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(int position) {
            mTxtTime.setText(getFullTime(position));

        }

        String getFullTime(int position) {
            return new StringBuilder(
                    mAlarmList.get(position).getHour() + "")
                    .append(" : ")
                    .append(mAlarmList.get(position).getMin()).toString();
        }
    }
}
