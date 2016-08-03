package com.song.multiitemlistview.viewholder;

import android.graphics.Color;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.song.multiitemlistview.R;
import com.song.multiitemlistview.bean.SmsInfo;
import com.song.multiitemlistview.util.ScreenUtil;


/**
 * Created by Jack on 2016/7/15.
 */
public class SmsMsgViewHolder extends MsgViewHolderBase {
    @Override
    protected int getContentResId() {
        return R.layout.item_message_contact_text;
    }

    @Override
    protected void inflateContentView() {

    }


    @Override
    protected void bindContentView() {
        TextView bodyTextView = findViewById(R.id.nim_message_item_text_body);
        layoutDirection(bodyTextView);
        bodyTextView.setTextColor(isReceivedMessage() ? Color.BLACK : Color.WHITE);
        bodyTextView.setText(((SmsInfo)(message.getCTInfo())).getSmsbody());
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
