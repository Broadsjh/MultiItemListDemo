package com.song.multiitemlistview.constant;

/**
 * Created by Jack.song on 2016/7/18.
 */
public enum MsgTypeEnum {
    sms(0, "短信"),
    callrecord(1, "通话记录");


    private final int value;
    final String sendMessageTip;

    private MsgTypeEnum(int var3, String var4) {
        this.value = var3;
        this.sendMessageTip = var4;
    }

    public final int getValue() {
        return this.value;
    }

    public final String getSendMessageTip() {
        return this.sendMessageTip;
    }
}
