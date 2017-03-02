package com.wll.googleplay_wll.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 王丽丽 on 2017/3/2.
 */

public abstract class BaseFragmentCommont extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        init();
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListenter();
    }

    /**
     * 初始化监听事件
     */
    public void initListenter(){

    }

    /**
     * 初始化数据
     */
    public void initData(){

    }

    /**
     * 初始化
     */
    private void init() {
    }

    public abstract View initView();
}
