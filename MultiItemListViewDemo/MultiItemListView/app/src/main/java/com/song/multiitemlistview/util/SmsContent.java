package com.song.multiitemlistview.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.song.multiitemlistview.bean.CTMessage;
import com.song.multiitemlistview.bean.SmsInfo;
import com.song.multiitemlistview.constant.MsgDirectionEnum;
import com.song.multiitemlistview.constant.MsgTypeEnum;
import com.song.multiitemlistview.constant.SmsEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取短息记录工具类
 * Created by Jack.song on 2016/7/14.
 */
public class SmsContent {
    private static final String TAG="SmsContent";
    private Activity activity;//这里有个activity对象，不知道为啥以前好像不要，现在就要了。自己试试吧。
    private Uri uri;
    private String phoneNumber;
    List<CTMessage> infos;

    public SmsContent(Activity activity, Uri uri,String pn) {
        infos = new ArrayList<CTMessage>();
        this.activity = activity;
        this.uri = uri;
        this.phoneNumber=pn;
    }

    /**
     * 33
     * Role:获取短信的各种信息 <BR>
     * 34
     * Date:2012-3-19 <BR>
     * 35
     * <p/>
     * 36
     *
     * @author CODYY)peijiangping
     * 37
     */
    public List<CTMessage> getSmsInfo() {
        String[] projection = new String[]{ "address", "person", "body","date","type"};
        ContentResolver cr = activity.getContentResolver();
        String where = " address = "+phoneNumber;
        Cursor cusor = cr.query(uri, projection, where, null, "date desc");
        //
        int nameColumn = cusor.getColumnIndex("person");
        int phoneNumberColumn = cusor.getColumnIndex("address");
        int smsbodyColumn = cusor.getColumnIndex("body");
        int dateColumn = cusor.getColumnIndex("date");
        int typeColumn = cusor.getColumnIndex("type");
        if (cusor != null) {
            while (cusor.moveToNext()) {
                CTMessage ctMessage = new CTMessage();
                ctMessage.setName(cusor.getString(nameColumn));
                ctMessage.setTimestamp(cusor.getLong(dateColumn));
                ctMessage.setTime(DateUtil.getStrTime(cusor.getLong(dateColumn)+""));
                ctMessage.setPhoneNumber(cusor.getString(phoneNumberColumn));
                SmsInfo smsInfo=new SmsInfo();
                smsInfo.setSmsbody(cusor.getString(smsbodyColumn));
                String smsType=cusor.getString(typeColumn);
                ctMessage.setMsgType(MsgTypeEnum.sms);
                if("1".equals(smsType)){
                    smsInfo.setSmsEnum(SmsEnum.RECEIVEE);
                    ctMessage.setDirect(MsgDirectionEnum.In);
                }else{
                    smsInfo.setSmsEnum(SmsEnum.SENDED);
                    ctMessage.setDirect(MsgDirectionEnum.Out);
                }
                ctMessage.setCTInfo(smsInfo);
                infos.add(ctMessage);
            }
            cusor.close();
        }
        return infos;
    }








    private void getMessage() {
        ContentResolver cr = activity.getContentResolver();
        String[] projection = new String[]{"body"};//"_id", "address", "person",, "date", "type
        String where = " address = '10010' AND date >  "
                + (System.currentTimeMillis() - 10 * 60 * 1000);
        where = " address = '10010'";
        Cursor cur = cr.query(uri, projection, where, null, "date desc");
        if (null == cur)
            return;
        if (cur.moveToNext()) {
            String number = cur.getString(cur.getColumnIndex("address"));//手机号
            String name = cur.getString(cur.getColumnIndex("person"));//联系人姓名列表
            String body = cur.getString(cur.getColumnIndex("body"));
            //这里我是要获取自己短信服务号码中的验证码~~
            Pattern pattern = Pattern.compile(" [a-zA-Z0-9]{10}");
            Matcher matcher = pattern.matcher(body);
            if (matcher.find()) {
                String res = matcher.group().substring(1, 11);
                LogUtil.d(TAG,"res:"+res);
            }
        }
    }



}
