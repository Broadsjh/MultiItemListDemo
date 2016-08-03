package com.baza.multiitemlistviewdemo.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    public static void setTimeZone() {
        TimeZone tz = TimeZone.getTimeZone("GMT+8");
        TimeZone.setDefault(tz);
    }






    /**
     * 将时间戳转为字符串
     * @param cc_time
     * @return
     */
    public static String getStrTime(String cc_time){
        String re_StrTime=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lcc_time=Long.valueOf(cc_time);
        re_StrTime=sdf.format(new Date(lcc_time));
        return  re_StrTime;
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static Long getCurrentTimestamp(){
        Long cts;
        Timestamp now = new Timestamp(System.currentTimeMillis());
        cts=now.getTime();
        return cts;
    }


   // 整数(秒数)转换为时分秒格式(xx:xx:xx)
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }


    // 整数(秒数)转换为时分秒格式(xx:xx'xx")
    public static String secToTime2(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "0\"";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                String min=unitFormat(minute);
                String sen=unitFormat(second);
                if("00".equals(min)){
                    timeStr=sen+"\"";
                }else{
                    timeStr =  min+ "\'" + sen+"\"";
                }

            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }



    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }


}
