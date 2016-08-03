package com.baza.multiitemlistviewdemo.adapter;


import com.baza.multiitemlistviewdemo.adapter.viewholder.TViewHolder;

/**
 * Created by Jack.song on 2016/7/15.
 */
public interface TAdapterDelegate {
    public int getViewTypeCount();
    public Class<? extends TViewHolder> viewHolderAtPosition(int position);
    public boolean enabled(int position);
}
