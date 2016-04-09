package com.gaincigarretprice.idiot.sun.view.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.gaincigarretprice.idiot.djlibrary.util.AlarmUtil;
import com.gaincigarretprice.idiot.sun.R;
import com.gaincigarretprice.idiot.sun.model.data.Alarm;
import com.gaincigarretprice.idiot.sun.model.data.dto.AlarmDTO;
import com.gaincigarretprice.idiot.sun.model.data.realm.AlarmObject;
import com.gaincigarretprice.idiot.sun.service.AlarmReceiveService;
import com.gaincigarretprice.idiot.sun.util.Constant;
import com.gaincigarretprice.idiot.sun.util.LogUtil;
import com.gaincigarretprice.idiot.sun.view.base.BaseActivity;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ladmusician on 3/8/16.
 */
public class ActivityAddAlarm extends BaseActivity {
    private final String TAG = "ACTIVITY_ADD_SWITCH";
    private Context mContext = null;
    private static final int ADD_ALARM_REPEAT_RESULT_CODE = 3;
    private static final int ADD_ALARM_SOUND_RESULT_CODE = -1;

    private AlarmDTO mAlarm = null;
    private AlarmManager mAlarmManger = null;

    @Bind(R.id.add_alarm_btn_back)
    RelativeLayout mBtnBack;
    @Bind(R.id.add_alarm_time)
    TimePicker mTimePicker;
    @Bind(R.id.add_alarm_txt_repeat)
    TextView mTxtRepeat;
    @Bind(R.id.add_alarm_txt_sound)
    TextView mTxtSound;


    private boolean isEditMode = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        ButterKnife.bind(this);
        init();

        int alarmId = getIntent().getIntExtra(Alarm.ALARM_ID, -1);

        if (isExistAlarmEditMode(alarmId)) {

            // TODO: 2016. 4. 9.  궁금하니 일단 만들고 MVP
            AlarmObject alarmObject = getEditTargetAlarmObject(alarmId);

            bindExistAlarmData(alarmObject);

        }
    }

    private AlarmObject getEditTargetAlarmObject(int alarmId) {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<AlarmObject> realmResults =
                realm.where(AlarmObject.class).equalTo("_alarmid", alarmId).findAll();

        return realmResults.size() > 0 ? realmResults.get(0) : null;

    }

    private void bindExistAlarmData(@Nullable AlarmObject alarmObject) {

        if (null == alarmObject) {
            LogUtil.print(TAG, "Error case : Get edit target Alarm fail");
            return;
        }

        if (Build.VERSION.SDK_INT >= 23) {

            mTimePicker.setHour(alarmObject.getHour());
            mTimePicker.setMinute(alarmObject.getMin());
        } else {
            mTimePicker.setCurrentHour(alarmObject.getHour());
            mTimePicker.setCurrentMinute(alarmObject.getMin());
        }

        isEditMode = true;
    }

    private boolean isExistAlarmEditMode(int alarmId) {
        return alarmId != -1;
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    private void init() {
        mContext = getApplicationContext();
        mAlarm = new AlarmDTO();
    }

    @OnClick({R.id.add_alarm_btn_back, R.id.add_alarm_repeat_container,
            R.id.add_alarm_sound_container, R.id.add_alarm_btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_alarm_btn_back:
                finish();
                break;
            case R.id.add_alarm_repeat_container:
                Intent intent = new Intent(this, ActivityAlarmRepeat.class);
                intent.putExtra(getString(R.string.KEY_ADD_ALARM_REPEAT), new boolean[]{true, false, false, false, false, false, false});
                startActivityForResult(intent, Constant.ADD_ALARM_REPEAT_REQUEST_CODE);
                break;
            case R.id.add_alarm_sound_container:
                showRingtonePickerDialog();
                break;
            case R.id.add_alarm_btn_submit:
                if(!isEditMode)
                    setAlarm();
                else {
                    // TODO: 2016. 4. 9.  알람 수정로직. 
                }
                finish();
                break;
        }
    }

    /**
     * open the ringtone system library by intent
     */
    private void showRingtonePickerDialog() {
        startActivityForResult(AlarmUtil.getRingtoneIntent(), 0);
    }

    // startActivity 결과값 받아오기;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case ADD_ALARM_SOUND_RESULT_CODE:    // ringtone
                dealRingtone(data);
                break;
            case ADD_ALARM_REPEAT_RESULT_CODE:     // repeat
                dealReapt(data);
                break;
            default:
                break;
        }
    }

    /**
     * get ringtone url, title
     *
     * @param data
     */
    void dealRingtone(Intent data) {
        Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
        mAlarm.setRingtone_url(uri.toString());
        if (uri != null) {
            Ringtone ringtone = RingtoneManager.getRingtone(this, uri);
            mAlarm.setRingtone_title(ringtone.getTitle(this));
            mTxtSound.setText(mAlarm.getRingtone_title());
        }
    }

    /**
     * set AlarmDTO repeat data
     */
    void dealReapt(Intent data) {
        boolean[] arrRepeat = data.getBooleanArrayExtra(getString(R.string.KEY_ADD_ALARM_REPEAT));
        mAlarm.setSun(arrRepeat[Constant.SUN]);
        mAlarm.setMon(arrRepeat[Constant.MON]);
        mAlarm.setTue(arrRepeat[Constant.TUE]);
        mAlarm.setWed(arrRepeat[Constant.WED]);
        mAlarm.setThur(arrRepeat[Constant.THUR]);
        mAlarm.setFri(arrRepeat[Constant.FRI]);
        mAlarm.setSat(arrRepeat[Constant.SAT]);
    }

    /**
     * register alarm by realm object id
     */
    void setAlarm() {
        mTimePicker.clearFocus();
        if (Build.VERSION.SDK_INT >= 23) {
            mAlarm.setHour(mTimePicker.getHour());
            mAlarm.setMin(mTimePicker.getMinute());
        } else {
            mAlarm.setHour(mTimePicker.getCurrentHour());
            mAlarm.setMin(mTimePicker.getCurrentMinute());
        }

        long triggerTime = AlarmUtil.calSetTimerSecond(mAlarm.getHour(), mAlarm.getMin());

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        AlarmObject alarmObject = realm.createObject(AlarmObject.class);
        alarmObject.set_alarmid(((int) new Date().getTime()));
        alarmObject.setHour(mAlarm.getHour());
        alarmObject.setMin(mAlarm.getMin());
        alarmObject.setSun(mAlarm.isSun());
        alarmObject.setMon(mAlarm.isMon());
        alarmObject.setTue(mAlarm.isTue());
        alarmObject.setWed(mAlarm.isWed());
        alarmObject.setThur(mAlarm.isThur());
        alarmObject.setFri(mAlarm.isFri());
        alarmObject.setSat(mAlarm.isSat());
        realm.commitTransaction();
        LogUtil.print(TAG, alarmObject.toString());

        boolean[] week = {mAlarm.isSun(), mAlarm.isMon(), mAlarm.isTue(), mAlarm.isWed(), mAlarm.isThur(),
                mAlarm.isFri(), mAlarm.isSat()};

        Intent alarmIntent = new Intent(mContext, AlarmReceiveService.class);
        alarmIntent.setAction(getString(R.string.ACTION_ALARM));
        alarmIntent.putExtra(getString(R.string.KEY_ALARM_REPEAT), week);
        alarmIntent.putExtra(getString(R.string.KEY_ALARM_ID), alarmObject.get_alarmid());
        alarmIntent.putExtra(getString(R.string.KEY_ALARM_RINGTONE), mAlarm.getRingtone_url());
        PendingIntent pi = PendingIntent.getService(mContext, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        getAlarmManager().setRepeating(AlarmManager.RTC_WAKEUP, triggerTime, AlarmManager.INTERVAL_DAY, pi);
        Log.e(TAG, "COMPLETE REGISTER ALARM");

    }

    private AlarmManager getAlarmManager() {
        if (mAlarmManger == null) {
            mAlarmManger = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        }
        return mAlarmManger;
    }
}
