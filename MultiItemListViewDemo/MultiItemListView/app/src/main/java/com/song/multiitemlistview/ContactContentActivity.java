package com.song.multiitemlistview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import com.song.multiitemlistview.adapter.MsgAdapter;
import com.song.multiitemlistview.adapter.TAdapterDelegate;
import com.song.multiitemlistview.bean.CTMessage;
import com.song.multiitemlistview.constant.AllFinalInfo;
import com.song.multiitemlistview.constant.MsgTypeEnum;
import com.song.multiitemlistview.util.CallRecordContent;
import com.song.multiitemlistview.util.SmsContent;
import com.song.multiitemlistview.viewholder.CallRecordsViewHolder;
import com.song.multiitemlistview.viewholder.SmsMsgViewHolder;
import com.song.multiitemlistview.viewholder.TViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created Jack.song ah on 2016/8/3.
 */
public class ContactContentActivity extends Activity implements TAdapterDelegate {


    public static final String CONTACT_PHONE_NUMBER = "phone_number";

    ListView lv_contact;
    TextView tv_no_data;

    String phoneNumber = "559";
    private List<CTMessage> allMessage = new ArrayList<CTMessage>();
    MsgAdapter mMsgAdapter;


    public static void start(Activity activity, String phoneNumber) {
        Intent intent = new Intent();
        intent.setClass(activity, ContactContentActivity.class);
        intent.putExtra(CONTACT_PHONE_NUMBER, phoneNumber);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactcontent);
        initView();
        phoneNumber = getIntent().getExtras().getString(CONTACT_PHONE_NUMBER);
        initData();
    }

    private void initView() {
        lv_contact = (ListView) findViewById(R.id.lv_contact);
        tv_no_data = (TextView) findViewById(R.id.tv_no_data);
    }


    private void initData() {
        //获取短信数据
        Uri uri = Uri.parse(AllFinalInfo.SMS_URI_ALL);
        SmsContent sc = new SmsContent(this, uri, phoneNumber);
        List<CTMessage> smsMessages = sc.getSmsInfo();
        allMessage.addAll(smsMessages);

        //获取通话记录
        new CallRecordContent(this, phoneNumber, new CallRecordContent.CallRecordListener() {
            @Override
            public void success(List<CTMessage> callRecordInfos) {
                allMessage.addAll(callRecordInfos);
                SortComparator comp = new SortComparator();
                Collections.sort(allMessage, comp);
                setAdapterView();
            }
        });
    }


    private void setAdapterView() {
        if (allMessage == null || allMessage.size() < 1) {
            tv_no_data.setVisibility(View.VISIBLE);
        }else{
            mMsgAdapter = new MsgAdapter(this, allMessage, this);
            lv_contact.setAdapter(mMsgAdapter);
            lv_contact.setSelection(mMsgAdapter.getCount() - 1);
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
        if (allMessage.get(position).getMsgType() == MsgTypeEnum.sms) {
            return SmsMsgViewHolder.class;
        } else if (allMessage.get(position).getMsgType() == MsgTypeEnum.callrecord) {
            return CallRecordsViewHolder.class;
        }
        return SmsMsgViewHolder.class;
    }

    @Override
    public boolean enabled(int position) {
        return false;
    }


    /**
     * 排序
     */
    class SortComparator implements Comparator<CTMessage> {
        @Override
        public int compare(CTMessage lhs, CTMessage rhs) {
            return lhs.getTimestamp().compareTo(rhs.getTimestamp());
        }
    }

}


