package com.wll.googleplay_wll.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.wll.googleplay_wll.base.BaseHolder;
import com.wll.googleplay_wll.base.HomeHolder;
import com.wll.googleplay_wll.base.MyBaseAdapter;
import com.wll.googleplay_wll.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王丽丽 on 2017/3/3.
 */
public class HomeAdapter extends MyBaseAdapter<HomeBean.ItemBean> {

    private List<HomeBean.ItemBean> datas= new ArrayList<>();
    public HomeAdapter(List<HomeBean.ItemBean> datas) {
        super(datas);
        this.datas = datas;
    }


    /**
     * 设置holder
     * @return
     */
    @Override
    public BaseHolder getMyBaseHolder() {

        return new HomeHolder();
    }


}
