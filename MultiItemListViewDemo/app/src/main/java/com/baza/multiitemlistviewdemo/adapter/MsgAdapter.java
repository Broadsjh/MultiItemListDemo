package com.baza.multiitemlistviewdemo.adapter;

import android.content.Context;


import com.baza.multiitemlistviewdemo.bean.SmsInfo;

import java.util.List;


/**
 * Created by Jack.song on 2016/7/15.
 */
public class MsgAdapter extends TAdapter<SmsInfo> {


    public MsgAdapter(Context context, List items, TAdapterDelegate delegate) {
        super(context, items, delegate);
    }


}
