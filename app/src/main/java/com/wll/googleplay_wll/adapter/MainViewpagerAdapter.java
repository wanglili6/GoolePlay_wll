package com.wll.googleplay_wll.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.TimeUtils;

import com.socks.library.KLog;
import com.wll.googleplay_wll.factory.FragmentFactory;

/**
 * Created by 王丽丽 on 2017/3/1.
 */

public class MainViewpagerAdapter extends FragmentStatePagerAdapter {
    public MainViewpagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    private String[] titles;

    public MainViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //获取fragment对象
        Fragment fragment = FragmentFactory.createFragment(position);
        return fragment;
    }

    @Override
    public int getCount() {
        if (titles!=null){
            return titles.length;
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
