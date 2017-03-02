package com.wll.googleplay_wll.fragment;

import android.graphics.Color;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.wll.googleplay_wll.base.BaseFragment;
import com.wll.googleplay_wll.base.LoadingPager;
import com.wll.googleplay_wll.utils.UIUtils;

/**
 * Created by 王丽丽 on 2017/3/1.
 * 专题
 */
public class SubjectFragment extends BaseFragment {

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

