package com.wll.googleplay_wll.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wll.googleplay_wll.base.BaseFragment;
import com.wll.googleplay_wll.base.LoadingPager;
import com.wll.googleplay_wll.utils.UIUtils;

/**
 * Created by 王丽丽 on 2017/3/1.
 *分类
 */
public class CategoryFragment extends BaseFragment {

    /**
     * 加载具体数据
     * @return
     */
    @Override
    protected LoadingPager.LoadedResult initData() {
        SystemClock.sleep(2000);
        return LoadingPager.LoadedResult.SUCCESS;
    }

    /**
     * 记载成功视图
     * @return
     */
    @Override
    public View initSuccess() {
        TextView textView  = new TextView(UIUtils.getContext());
        textView.setText(getClass().getSimpleName());
        textView.setTextColor(Color.RED);
        return textView;
    }
}
