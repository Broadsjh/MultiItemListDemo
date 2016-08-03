package com.baza.multiitemlistviewdemo.constant;

import android.provider.CallLog;

/**
 * Created by Jack.song on 2016/7/27.
 */
public enum  CallRecordEnum {
    INCOMING("呼入", CallLog.Calls.INCOMING_TYPE),
    OUTGOING("呼出", CallLog.Calls.OUTGOING_TYPE),
    MISSED("未接",CallLog.Calls.MISSED_TYPE),
    DEFAIL("挂断", CallLog.Calls.VOICEMAIL_TYPE);


    /**
     * 电话呼叫类型码
     */
    private int typeCode;
    /**
     *电话呼叫类型
     */
    private String typeName;

    // 构造方法
    private CallRecordEnum(String typeName, int typeCode) {
        this.typeName = typeName;
        this.typeCode = typeCode;
    }

    // 普通方法
    public static String getTypeName(int typeCode) {
        for (CallRecordEnum c : CallRecordEnum.values()) {
            if (c.getTypeCode() == typeCode) {
                return c.typeName;
            }
        }
        return null;
    }

    // get set 方法
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

}
