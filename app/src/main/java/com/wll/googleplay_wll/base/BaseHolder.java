package com.wll.googleplay_wll.base;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wll.googleplay_wll.R;
import com.wll.googleplay_wll.utils.UIUtils;

/**
 * Created by 王丽丽 on 2017/3/3.
 * cutroller---
 * 提供视图
 * 加载数据
 * 进行数据和视图间的绑定
 *
 */

public abstract  class BaseHolder<T> {
    /**
     *  初始化视图
     */
    public View mViewHolder;//返回一个view
    private T datas;//接受数据
    public BaseHolder() {
        mViewHolder = initHolder();
        //找一个符合条件的holder,绑定在自己身上
        mViewHolder.setTag(this);
    }

    /**
     * 初始化视图,
     * 因为不知道成功视图具体样子
     * 所以定义成抽象方法
     * @return
     */
    public abstract View initHolder() ;

    /**
     * @des 1.接收数据
     * @des 2.数据和视图的绑定
     */
    public void setDataAndRefreshHolderView(T data) {
        //保存数据到成员变量
        datas = data;
        //进行数据的视图的绑定
        refreshHolderView(data);
    }

    /**
     * @des 数据和视图的绑定操作
     */
    public abstract void refreshHolderView(T data);


}
