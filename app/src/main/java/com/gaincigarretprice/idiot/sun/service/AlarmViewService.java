package com.gaincigarretprice.idiot.sun.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gaincigarretprice.idiot.sun.R;
import com.gaincigarretprice.idiot.sun.model.helper.PreferenceHelper;
import com.gaincigarretprice.idiot.sun.service.AlarmKlaxonService;

public class AlarmViewService extends Service {
    private static final String TAG = "SERVICE_ALARM_VIEW";
    private TextView mTxtMain;
    private ImageButton mImgBtnCancel;
    private WindowManager mWindowManager;
    private int panelWidth;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if(action.equals(getString(R.string.ACTION_ALARM_VIEW))) {

            panelWidth = getPreferenceHelper().getValue(getString(R.string.KEY_DEVICE_WIDTH), -1);
            mTxtMain = new TextView(this);
            mTxtMain.setBackgroundResource(R.drawable.img_splash);
            mTxtMain.setWidth(panelWidth);
            mTxtMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "CLICK");
                    Intent klaxonIntent = new Intent(AlarmViewService.this, AlarmKlaxonService.class);
                    stopService(klaxonIntent);

                    Intent viewIntent = new Intent(AlarmViewService.this, AlarmViewService.class);
                    stopService(viewIntent);
                    stopSelf();
                }
            });
            WindowManager.LayoutParams mainImageParams = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);

            mainImageParams.x = 0;
            mainImageParams.y = 0;

            mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            mWindowManager.addView(mTxtMain, mainImageParams);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        if(mTxtMain != null) mWindowManager.removeView(mTxtMain);
        if(mImgBtnCancel != null) mWindowManager.removeView(mImgBtnCancel);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public PreferenceHelper getPreferenceHelper() {
        return new PreferenceHelper(this);
    }
}
