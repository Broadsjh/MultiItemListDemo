package com.baza.multiitemlistviewdemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.baza.multiitemlistviewdemo.adapter.viewholder.TViewHolder;
import com.baza.multiitemlistviewdemo.util.LogUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jack.song on 2016/7/15.
 */
public class TAdapter<T> extends BaseAdapter implements IViewReclaimer{
    private static final String TAG="TAdapter";

    protected Context context;
    protected List<T> items;

    private final TAdapterDelegate delegate;
    private final LayoutInflater inflater;
    private Set<IScrollStateListener> listeners;
    private final Map<Class<?>, Integer> viewTypes;


    public TAdapter(Context context, List<T> items, TAdapterDelegate delegate) {
        this.context=context;
        this.items=items;
        this.delegate=delegate;
        this.inflater=LayoutInflater.from(context);
        this.viewTypes = new HashMap<Class<?>, Integer>(getViewTypeCount());
        this.listeners = new HashSet<IScrollStateListener>();
    }

    @Override
    public int getCount() {
        return items==null?0:items.size();
    }

    @Override
    public Object getItem(int position) {
        return position<getCount()?items.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=viewAtPosition(position);
        }
        TViewHolder holder=(TViewHolder)convertView.getTag();
        holder.setPosition(position);
        try {
            LogUtil.d(TAG,"position:"+position+"---item:"+getItem(position));
            holder.refresh(getItem(position));
        } catch (RuntimeException e) {
        }
        if (holder instanceof IScrollStateListener) {
            listeners.add(holder);
        }
        return convertView;
    }


    @Override
    public int getViewTypeCount() {
        return delegate.getViewTypeCount();
    }


    @Override
    public int getItemViewType(int position) {
        if (getViewTypeCount() == 1) {
            return 0;
        }

        Class<?> clazz = delegate.viewHolderAtPosition(position);
        if (viewTypes.containsKey(clazz)) {
            return viewTypes.get(clazz);
        } else {
            int type = viewTypes.size();
            if (type < getViewTypeCount()) {
                viewTypes.put(clazz, type);
                return type;
            }
            return 0;
        }
    }

    public View viewAtPosition(int position){

        TViewHolder holder=null;
        View view;
        try {
            Class<?> viewHolder=delegate.viewHolderAtPosition(position);
            holder=(TViewHolder)viewHolder.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        view=holder.getView(inflater);
        view.setTag(holder);
        holder.setContext(view.getContext());
        return view;
    }

    @Override
    public void reclaimView(View view) {
        if (view == null) {
            return;
        }
        Log.i(TAG,"reclaimView");
        TViewHolder holder = (TViewHolder) view.getTag();
        if (holder != null) {
            holder.reclaim();
            holder=null;
            listeners.remove(holder);

        }
    }
}
