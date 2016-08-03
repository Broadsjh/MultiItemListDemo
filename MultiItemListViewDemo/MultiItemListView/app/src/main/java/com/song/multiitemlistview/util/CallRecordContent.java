package com.song.multiitemlistview.util;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;

import com.song.multiitemlistview.bean.CTMessage;
import com.song.multiitemlistview.bean.CallRecordInfo;
import com.song.multiitemlistview.constant.CallRecordEnum;
import com.song.multiitemlistview.constant.MsgDirectionEnum;
import com.song.multiitemlistview.constant.MsgTypeEnum;
import com.song.multiitemlistview.permission.PermissionsManager;
import com.song.multiitemlistview.permission.PermissionsResultAction;

import java.util.ArrayList;
import java.util.List;


/**
 * 获取通话记录工具类
 * Created by Jack.song on 2016/7/18.
 */
public class CallRecordContent {
    private static final String TAG = "PhoneCallContent";

    Activity mActivity;
    String mPhoneNumber;
    CallRecordListener mCallRecordListener;


    public CallRecordContent(Activity activity, String phoneNumber, CallRecordListener callRecordListener) {
        this.mActivity = activity;
        this.mPhoneNumber = phoneNumber;
        this.mCallRecordListener = callRecordListener;
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(mActivity,
                new String[]{Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG}, new PermissionsResultAction() {

                    @Override
                    public void onGranted() {
                        getPcInfo();
                    }

                    @Override
                    public void onDenied(String permission) {
                    }
                }
        );
    }


    public void getPcInfo() {
        List<CTMessage> mRecordList = null;
        final ContentResolver contentResolver = mActivity.getContentResolver();
        Cursor cursor = null;
        String where = CallLog.Calls.NUMBER + " = " + mPhoneNumber;
        if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            return ;
        }
        cursor = contentResolver.query(
                CallLog.Calls.CONTENT_URI, null, where, null,
                CallLog.Calls.DATE + " desc");
        try {
            if (cursor == null)
                return ;
            mRecordList = new ArrayList<CTMessage>();
            while (cursor.moveToNext()) {
                CTMessage ctMessage = new CTMessage();
                ctMessage.setName(cursor.getString(cursor
                        .getColumnIndex(CallLog.Calls.CACHED_NAME)));
                ctMessage.setPhoneNumber(cursor.getString(cursor
                        .getColumnIndex(CallLog.Calls.NUMBER)));

                ctMessage.setTimestamp(cursor.getLong(cursor
                        .getColumnIndex(CallLog.Calls.DATE)));
                ctMessage.setTime( DateUtil.getStrTime(cursor.getLong(cursor
                        .getColumnIndex(CallLog.Calls.DATE))+""));

                ctMessage.setMsgType(MsgTypeEnum.callrecord);
                int callsType=Integer.parseInt(cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE)));
                CallRecordInfo callRecordInfo=new CallRecordInfo();
                callRecordInfo.setDuration(cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DURATION)));
                CallRecordEnum callRecordEnum;
                String showStr=DateUtil.secToTime2((int)callRecordInfo.getDuration());
                switch (callsType) {
                    case CallLog.Calls.INCOMING_TYPE:
                        callRecordEnum= CallRecordEnum.INCOMING;
                        ctMessage.setDirect(MsgDirectionEnum.In);
                        break;
                    case CallLog.Calls.OUTGOING_TYPE:
                        if(callRecordInfo.getDuration()<=0){
                            callRecordEnum= CallRecordEnum.DEFAIL;
                            showStr="";
                        }else{
                            callRecordEnum= CallRecordEnum.OUTGOING;
                        }
                        ctMessage.setDirect(MsgDirectionEnum.Out);
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        callRecordEnum= CallRecordEnum.MISSED;
                        ctMessage.setDirect(MsgDirectionEnum.In);
                        showStr="";
                        break;
                    default:
                        callRecordEnum= CallRecordEnum.DEFAIL;
                        ctMessage.setDirect(MsgDirectionEnum.Out);
                        break;
                }
//                      int photoIdIndex = cursor.getColumnIndex(CACHED_PHOTO_ID);
//                      if (photoIdIndex >= 0) {
//                          record.cachePhotoId = cursor.getLong(photoIdIndex);
//                      }

                callRecordInfo.setShowStr(callRecordEnum.getTypeName()+showStr);
                callRecordInfo.setCallRecordEnum(callRecordEnum);
                callRecordInfo.set_new(cursor.getInt(cursor
                        .getColumnIndex(CallLog.Calls.NEW)));
                ctMessage.setCTInfo(callRecordInfo);
                mRecordList.add(ctMessage);
            }
        }catch (Exception e){

        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        mCallRecordListener.success(mRecordList);
    }




    public interface CallRecordListener{

        public void success(List<CTMessage> callRecordInfos);

    }

}
