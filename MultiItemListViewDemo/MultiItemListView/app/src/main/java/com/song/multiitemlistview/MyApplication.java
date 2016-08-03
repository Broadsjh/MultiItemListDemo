package com.song.multiitemlistview;

import android.app.Application;

import com.song.multiitemlistview.util.ScreenUtil;


/**
 * Created Jack.song ah on 2016/8/3.
 */
public class MyApplication extends Application {

    static MyApplication myApplication;
    public static MyApplication getMyApplication(){
        return  myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        ScreenUtil.init(this);
    }
}
