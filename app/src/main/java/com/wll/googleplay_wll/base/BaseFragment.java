package com.wll.googleplay_wll.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wll.googleplay_wll.utils.UIUtils;

/**
 * Created by 王丽丽 on 2017/3/2.
 */

public abstract  class BaseFragment extends Fragment {

    private LoadingPager mLoaddingPage;

    /**
     * 获取loadingPager对象
     * @return
     */
    public LoadingPager getmLoaddingPage() {
        return mLoaddingPage;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLoaddingPage = new LoadingPager(UIUtils.getContext()) {
            @Override
            public View initSuccess() {
                return BaseFragment.this.initSuccess();
            }

            @Override
            protected LoadedResult initData() {
                return BaseFragment.this.initData();
            }
        };

//        //触发加载数据
//        mLoaddingPage.triggerLoadData();

        //4种视图中的一种(加载中,错误,空,成功)
        return mLoaddingPage;
    }

    /**
     * 获取加载数据的方法
     * @return
     */
    protected abstract LoadingPager.LoadedResult initData();

    /**
     * 获取成功视图
     * @return
     */

    public abstract View initSuccess() ;


}
