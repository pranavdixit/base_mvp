package com.idme.common;

import android.content.Context;
import android.util.Log;

import com.idme.BuildConfig;

import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by pranav.dixit on 02/04/18.
 */

public class AppLog {

    private static final String TAG = "EIDME_LOG";
    private static final boolean  ENABLE_LOGS = BuildConfig.DEBUG;


    private AppLog() {
    }


    public static void v(String msg) {
        if (ENABLE_LOGS) {
            Log.v(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (ENABLE_LOGS) {
            Log.i(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (ENABLE_LOGS) {
            Log.e(TAG, msg);

        }
    }
}
