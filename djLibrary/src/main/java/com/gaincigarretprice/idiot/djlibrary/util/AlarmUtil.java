package com.gaincigarretprice.idiot.djlibrary.util;

import android.content.Intent;
import android.media.RingtoneManager;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by ladmusician on 4/6/16.
 */
public class AlarmUtil {
    /**
     * get ringtone list
     * @return
     */
    public static Intent getRingtoneIntent() {
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,RingtoneManager.TYPE_ALL);

        return intent;
    }

    /**
     * get trigger time
     * @param hour
     * @param min
     * @return
     */
    public static long calSetTimerSecond (int hour, int min) {
        GregorianCalendar currentTime = new GregorianCalendar();
        GregorianCalendar triggerTime = new GregorianCalendar();

        int currentYear = currentTime.get(Calendar.YEAR);
        int currentMonth = currentTime.get(Calendar.MONTH);
        int currentDay = currentTime.get(Calendar.DAY_OF_MONTH);

        triggerTime.set(currentYear, currentMonth, currentDay, hour, min, 00);

        return triggerTime.getTimeInMillis();
    }
}
