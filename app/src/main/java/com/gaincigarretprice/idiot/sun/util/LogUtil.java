package com.gaincigarretprice.idiot.sun.util;

import android.util.Log;

import com.gaincigarretprice.idiot.sun.BuildConfig;

/**
 * Created by ladmusician on 4/7/16.
 */
public class LogUtil {
    public static void print(String tag, String msg) {
        if(BuildConfig.DEBUG) Log.e(tag, msg);
    }
}
