package com.song.multiitemlistview.viewholder;

import android.graphics.Color;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.song.multiitemlistview.R;
import com.song.multiitemlistview.bean.CallRecordInfo;
import com.song.multiitemlistview.util.LogUtil;
import com.song.multiitemlistview.util.ScreenUtil;


/**
 * Created by Jack.song on 2016/7/15.
 */
public class CallRecordsViewHolder extends MsgViewHolderBase {

    private static final String TAG="CallRecordsViewHolder";
    @Override
    protected int getContentResId() {
        return R.layout.item_message_contact_callrecords;
    }

    @Override
    protected void inflateContentView() {

    }


    @Override
    protected void bindContentView() {
        TextView bodyTextView = findViewById(R.id.message_item_callrecords_body);
        layoutDirection(bodyTextView);
        bodyTextView.setTextColor(isReceivedMessage() ? Color.BLACK : Color.WHITE);
        CallRecordInfo callRecordInfo=(CallRecordInfo)message.getCTInfo();
        String str=callRecordInfo.getShowStr();
        LogUtil.d(TAG,"str:"+str);
        bodyTextView.setText(callRecordInfo.getShowStr());
        bodyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onItemClick();
            }
        });
        bodyTextView.setMovementMethod(LinkMovementMethod.getInstance());
        //bodyTextView.setOnLongClickListener(longClickListener);
    }

    private void layoutDirection(TextView bodyTextView) {
        if (isReceivedMessage()) {
            bodyTextView.setBackgroundResource(R.drawable.message_item_left_selector);
            bodyTextView.setPadding(ScreenUtil.dip2px(15), ScreenUtil.dip2px(8), ScreenUtil.dip2px(10), ScreenUtil.dip2px(8));
        } else {
            bodyTextView.setBackgroundResource(R.drawable.message_item_right_selector);
            bodyTextView.setPadding(ScreenUtil.dip2px(10), ScreenUtil.dip2px(8), ScreenUtil.dip2px(15), ScreenUtil.dip2px(8));
        }
    }


}
