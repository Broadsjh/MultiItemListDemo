package com.song.multiitemlistview.util;


import android.util.Log;

import com.song.multiitemlistview.BuildConfig;


/**
 * Created by Jack.song on 2016/5/20.
 * Title : log
 * Note :
 */
public class LogUtil {
    private static final boolean isLoginEnable = BuildConfig.LOG_LEVEL > 0;

    private static final String TAG = "MultiItemListViewDemo";

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (isLoginEnable)
            Log.d(tag, msg + "");
    }


    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (isLoginEnable)
            Log.e(tag, msg + "");
    }


}
