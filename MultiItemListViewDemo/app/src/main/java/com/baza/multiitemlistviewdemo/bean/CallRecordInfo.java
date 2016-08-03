package com.baza.multiitemlistviewdemo.bean;


import com.baza.multiitemlistviewdemo.constant.CallRecordEnum;

/**
 * Created by Jack.song on 2016/7/18.
 * 电话记录数据
 */
public class CallRecordInfo extends CTInfo {


    /**
     * 呼叫类型
     */
    private CallRecordEnum callRecordEnum;
    /**
     * 持续时间
     */
    private long duration;
    private int _new;
    private String showStr;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int get_new() {
        return _new;
    }

    public void set_new(int _new) {
        this._new = _new;
    }

    public CallRecordEnum getCallRecordEnum() {
        return callRecordEnum;
    }

    public void setCallRecordEnum(CallRecordEnum callRecordEnum) {
        this.callRecordEnum = callRecordEnum;
    }

    public String getShowStr() {
        return showStr;
    }

    public void setShowStr(String showStr) {
        this.showStr = showStr;
    }

    @Override
    public String toString() {
        return "";
    }
}
