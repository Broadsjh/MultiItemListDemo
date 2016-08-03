package com.baza.multiitemlistviewdemo.constant;

/**
 * Created by Jack.song on 2016/7/27.
 */
public enum SmsEnum {
    RECEIVEE("接受", 1),
    SENDED("发出", 2);

    /**
     * 短信来源类型码
     */
    private int typeCode;
    /**
     *短信来源类型
     */
    private String typeName;

    // 构造方法
    private SmsEnum(String typeName, int typeCode) {
        this.typeName = typeName;
        this.typeCode = typeCode;
    }

    // 普通方法
    public static String getTypeName(int typeCode) {
        for (SmsEnum c : SmsEnum.values()) {
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
