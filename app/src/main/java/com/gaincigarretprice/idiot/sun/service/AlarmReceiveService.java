package com.gaincigarretprice.idiot.sun.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class AlarmReceiveService extends Service {
    private final String TAG = "SERVER_ALARM_FIRE";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "GOT Service");
        String action = intent.getAction();
        if(action.equals(getString(R.string.ACTION_ALARM))) {
            Intent viewIntent = new Intent(this, AlarmViewService.class);
            viewIntent.setAction(getString(R.string.ACTION_ALARM_VIEW));
            startService(viewIntent);

            String ringtoneURI = intent.getStringExtra(getString(R.string.KEY_ALARM_RINGTONE));
            Intent klaxonIntent = new Intent(this,AlarmKlaxonService.class);
            klaxonIntent.setAction(getString(R.string.ACTION_ALARM_KLAXON));
            klaxonIntent.putExtra(getString(R.string.KEY_ALARM_RINGTONE), ringtoneURI);
            startService(klaxonIntent);
        }

        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }
}
