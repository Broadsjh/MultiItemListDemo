package com.baza.multiitemlistviewdemo.bean;



import com.baza.multiitemlistviewdemo.constant.MsgDirectionEnum;
import com.baza.multiitemlistviewdemo.constant.MsgTypeEnum;

import java.io.Serializable;

/**
 * Created by Jack on 2016/7/15.
 * 数据基类
 */
public class CTMessage implements Serializable{

    

    /**
     * 联系人
     */
    private String name;
    /**
     * 联系电话
     */
    private String phoneNumber;
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 普通时间
     */
    private String time;
    /**
     * 显示的方向
     */
    private MsgDirectionEnum direct;
    private String fromAccount;
    private MsgTypeEnum msgType;
    private CTInfo cTInfo;


    public CTInfo getCTInfo() {
        return cTInfo;
    }

    public void setCTInfo(CTInfo cTInfo) {
        this.cTInfo = cTInfo;
    }

    public MsgTypeEnum getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgTypeEnum msgType) {
        this.msgType = msgType;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MsgDirectionEnum getDirect() {
        return direct;
    }

    public void setDirect(MsgDirectionEnum direct) {
        this.direct = direct;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }
















}
