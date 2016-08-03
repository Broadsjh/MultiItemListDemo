package com.baza.multiitemlistviewdemo.adapter.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.baza.multiitemlistviewdemo.adapter.IScrollStateListener;


/**
 * Created by Jack.song on 2016/7/15.
 */
public abstract class TViewHolder implements IScrollStateListener {

    protected Context context;

    protected View view;

    protected int position;


    public void setContext(Context context){
        this.context=context;
    }
    protected abstract int getResId();

    protected abstract void inflate();

    public abstract void refresh(Object item);

    @Override
    public void reclaim() {
    }

    @Override
    public void onImmutable() {
    }

    public  View getView(LayoutInflater inflater){
        int resId=getResId();
        view=inflater.inflate(resId,null);
        inflate();
        return  view;
    }

    public <T extends View> T findViewById(int resId){
        return (T)(view.findViewById(resId));
    }

    public void setPosition(int position) {
        this.position = position;
    }


}
