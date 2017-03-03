package com.wll.googleplay_wll.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wll.googleplay_wll.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by 王丽丽 on 2017/3/3.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private List<T> datas;

    public MyBaseAdapter(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        if (datas != null) {
            return datas.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (datas != null) {
            return datas.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseHolder holder = null;
        if (view == null) {
            holder = getMyBaseHolder();
        } else {
            holder = (BaseHolder) view.getTag();
        }
        Object data = datas.get(i);
        holder.setDataAndRefreshHolderView(data);
        return holder.mViewHolder;

    }

    /**
     * 动态返回一个holder
     * @return
     */
    public abstract BaseHolder getMyBaseHolder();
}
