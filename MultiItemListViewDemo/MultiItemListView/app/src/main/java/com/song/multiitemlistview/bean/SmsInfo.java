package com.song.multiitemlistview.bean;


import com.song.multiitemlistview.constant.SmsEnum;

/**
 * 短信数据
 * Created by Jack.song on 2016/7/14.
 */
public class SmsInfo extends CTInfo {


    /**
     * 短信内容
     */
    private String smsbody;

    /***
     * 短信类型1是接收到的，2是已发出
     */

    private SmsEnum smsEnum;

    public String getSmsbody() {
        return smsbody;
    }

    public void setSmsbody(String smsbody) {
        this.smsbody = smsbody;
    }

    public SmsEnum getSmsEnum() {
        return smsEnum;
    }

    public void setSmsEnum(SmsEnum smsEnum) {
        this.smsEnum = smsEnum;
    }
}
