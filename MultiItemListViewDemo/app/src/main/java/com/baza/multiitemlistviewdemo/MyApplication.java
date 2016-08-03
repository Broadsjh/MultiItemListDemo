package com.baza.multiitemlistviewdemo;

import android.app.Application;

import com.baza.multiitemlistviewdemo.util.ScreenUtil;

/**
 * Created by ah on 2016/8/3.
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
