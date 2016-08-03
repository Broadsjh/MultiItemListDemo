package com.baza.multiitemlistviewdemo.adapter.viewholder;

import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baza.multiitemlistviewdemo.R;
import com.baza.multiitemlistviewdemo.bean.CTMessage;
import com.baza.multiitemlistviewdemo.constant.MsgDirectionEnum;
import com.baza.multiitemlistviewdemo.util.LogUtil;
import com.baza.multiitemlistviewdemo.view.CircleImageView;


/**
 * Created by Jack.song on 2016/7/15.
 */
public abstract class MsgViewHolderBase extends TViewHolder {


    private static final String TAG="MsgViewHolderBase";
    protected CTMessage message;

    protected TextView nameTextView;
    protected TextView timeTextView;
    protected FrameLayout contentContainer;
    protected LinearLayout nameContainer;

    private CircleImageView avatarLeft;
    private CircleImageView avatarRight;


    @Override
    protected int getResId() {
        return R.layout.item_message_contact;
    }
    // 返回具体消息类型内容展示区域的layout res id`
    abstract protected int getContentResId();


    @Override
    protected void inflate() {
        timeTextView = findViewById(R.id.message_item_time);
        avatarLeft = findViewById(R.id.message_item_portrait_left);
        avatarRight = findViewById(R.id.message_item_portrait_right);
        nameTextView = findViewById(R.id.message_item_nickname);
        contentContainer = findViewById(R.id.message_item_content);
        nameContainer = findViewById(R.id.message_item_name_layout);
        View.inflate(view.getContext(),getContentResId(), contentContainer);
        inflateContentView();
    }

    // 在该接口中根据layout对各控件成员变量赋值
    abstract protected void inflateContentView();


    // 将消息数据项与内容的view进行绑定
    abstract protected void bindContentView();


    @Override
    public final void refresh(Object item) {
        message = (CTMessage) item;
        setHeadImageView();
        setTimeTextView();
        setContent();
        bindContentView();
    }


    private void setHeadImageView() {
        avatarLeft.setImageResource(R.drawable.avatar_def);
        avatarRight.setImageResource(R.drawable.avatar_def);
        CircleImageView show = isReceivedMessage() ? avatarLeft : avatarRight;
        CircleImageView hide = isReceivedMessage() ? avatarRight : avatarLeft;
        if (isMiddleItem()) {
            show.setVisibility(View.GONE);
        } else {
            show.setVisibility(View.VISIBLE);
            //show.loadBuddyAvatar(message.getFromAccount());
        }
        if (!isShowHeadImage()) {
            show.setVisibility(View.GONE);
        }
        hide.setVisibility(View.GONE);
    }


    /**
     * 设置时间显示
     */
    private void setTimeTextView() {
        timeTextView.setVisibility(View.VISIBLE);
        String text = message.getTime();
        timeTextView.setText(text);
    }






    // 判断消息方向，是否是接收到的消息
    protected boolean isReceivedMessage() {
        return message.getDirect() == MsgDirectionEnum.In;
    }

    // 返回该消息是不是居中显示
    protected boolean isMiddleItem() {
        return false;
    }

    // 是否显示头像，默认为显示
    protected boolean isShowHeadImage() {
        return true;
    }

    // 当是接收到的消息时，内容区域背景的drawable id
    protected int leftBackground() {
        return R.drawable.message_item_left_selector;
    }

    // 当是发送出去的消息时，内容区域背景的drawable id
    protected int rightBackground() {
        return R.drawable.message_item_right_selector;
    }

    // 设置FrameLayout子控件的gravity参数
    protected final void setGravity(View view, int gravity) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)view.getLayoutParams();
        params.gravity = gravity;
    }


    private void setContent() {

        LinearLayout bodyContainer = (LinearLayout) view.findViewById(R.id.message_item_body);
        if (isMiddleItem()) {
            setGravity(bodyContainer, Gravity.CENTER);
        } else {
            if (isReceivedMessage()) {
                setGravity(bodyContainer, Gravity.LEFT);
               // contentContainer.setBackgroundResource(leftBackground());
            } else {
                setGravity(bodyContainer, Gravity.RIGHT);
                //contentContainer.setBackgroundResource(rightBackground());
            }
        }
    }



}
