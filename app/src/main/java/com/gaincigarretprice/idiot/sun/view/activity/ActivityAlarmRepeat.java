package com.gaincigarretprice.idiot.sun.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.gaincigarretprice.idiot.sun.util.Constant;
import com.gaincigarretprice.idiot.sun.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ladmusician on 3/8/16.
 */
public class ActivityAlarmRepeat extends BaseActivity {
    private final String TAG = "ACTIVITY_ALARM_REPEAT";
    private Context mContext = null;
    private boolean[] mCheckList = {false, false, false, false, false, false, false};
    private List<ImageView> mImgList = null;

    @Bind(R.id.add_alarm_repeat_sun_img)
    ImageView mImgSun;
    @Bind(R.id.add_alarm_repeat_mon_img)
    ImageView mImgMon;
    @Bind(R.id.add_alarm_repeat_tues_img)
    ImageView mImgTues;
    @Bind(R.id.add_alarm_repeat_wed_img)
    ImageView mImgWed;
    @Bind(R.id.add_alarm_repeat_thur_img)
    ImageView mImgThur;
    @Bind(R.id.add_alarm_repeat_fri_img)
    ImageView mImgFri;
    @Bind(R.id.add_alarm_repeat_sat_img)
    ImageView mImgSat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm_repeat);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    private void init() {
        mContext = getApplicationContext();

        mImgList = new ArrayList<>();
        mImgList.add(mImgSun);
        mImgList.add(mImgMon);
        mImgList.add(mImgTues);
        mImgList.add(mImgWed);
        mImgList.add(mImgThur);
        mImgList.add(mImgFri);
        mImgList.add(mImgSat);

        Intent intent = getIntent();
        mCheckList = intent.getBooleanArrayExtra(getString(R.string.KEY_ADD_ALARM_REPEAT));

        if (mCheckList == null) {
            mCheckList = new boolean[] {false, false, false, false, false, false, false};
        } else {
            for(int i = 0; i < mCheckList.length; i++) {
                selectDay(i, true);
            }
        }
    }

    @OnClick({R.id.add_alarm_btn_back, R.id.add_alarm_repeat_sun_container, R.id.add_alarm_repeat_mon_container,
            R.id.add_alarm_repeat_tues_container, R.id.add_alarm_repeat_wed_container, R.id.add_alarm_repeat_thur_container,
            R.id.add_alarm_repeat_fri_container, R.id.add_alarm_repeat_sat_container, R.id.add_alarm_btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_alarm_btn_back:
                Intent intent = new Intent();
                intent.putExtra(getString(R.string.KEY_ADD_ALARM_REPEAT), mCheckList);
                setResult(Constant.ADD_ALARM_REPEAT_RESULT_CODE, intent);
                finish();
                break;
            case R.id.add_alarm_btn_submit:
                Intent submitIntent = new Intent();
                submitIntent.putExtra(getString(R.string.KEY_ADD_ALARM_REPEAT), mCheckList);
                setResult(Constant.ADD_ALARM_REPEAT_RESULT_CODE, submitIntent);
                finish();
                break;
            case R.id.add_alarm_repeat_sun_container:
                selectDay(0, false);
                break;
            case R.id.add_alarm_repeat_mon_container:
                selectDay(1, false);
                break;
            case R.id.add_alarm_repeat_tues_container:
                selectDay(2, false);
                break;
            case R.id.add_alarm_repeat_wed_container:
                selectDay(3, false);
                break;
            case R.id.add_alarm_repeat_thur_container:
                selectDay(4, false);
                break;
            case R.id.add_alarm_repeat_fri_container:
                selectDay(5, false);
                break;
            case R.id.add_alarm_repeat_sat_container:
                selectDay(6, false);
                break;
        }
    }

    void selectDay(int position, boolean isInit) {
        if (mCheckList[position]) {
            mImgList.get(position).setVisibility(isInit ? View.VISIBLE : View.GONE);
            if(!isInit) mCheckList[position] = false;
        }
        else {
            mImgList.get(position).setVisibility(isInit ? View.GONE : View.VISIBLE);
            if(!isInit) mCheckList[position] = true;
        }
    }
}
