package com.wll.googleplay_wll.factory;

import android.support.v4.app.Fragment;

import com.wll.googleplay_wll.base.BaseFragment;
import com.wll.googleplay_wll.fragment.AppFragment;
import com.wll.googleplay_wll.fragment.CategoryFragment;
import com.wll.googleplay_wll.fragment.GameFragment;
import com.wll.googleplay_wll.fragment.HomeFragment;
import com.wll.googleplay_wll.fragment.HotFragment;
import com.wll.googleplay_wll.fragment.RecommendFragment;
import com.wll.googleplay_wll.fragment.SubjectFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王丽丽 on 2017/3/1.
 */

public class FragmentFactory  {
    public static final int FRAGMENT_HOME = 0;//首页
    public static final int FRAGMENT_APP = 1;//应用
    public static final int FRAGMENT_GAME = 2;//游戏
    public static final int FRAGMENT_SUBJECT = 3;//专题
    public static final int FRAGMENT_RECOMMEND = 4;//推荐
    public static final int FRAGMENT_CATEGORY = 5;//分类
    public static final int FRAGMENT_HOT = 6;//排行
    public  static Map<Integer, BaseFragment> fragmentMap=new HashMap<>();

    /**
     * 初始化fragment
     * @param position
     */
    public static BaseFragment createFragment(int position){
        BaseFragment fragment = null;
        //优先缓存集合中取出来====触发加载数据的时机
        if (fragmentMap.containsKey(position)) {
            fragment = fragmentMap.get(position);
            return fragment;
        }
        switch (position){
            case FRAGMENT_HOME:
                fragment = new HomeFragment();//首页
                break;
            case FRAGMENT_APP:
                fragment = new AppFragment();//应用
                break;
            case FRAGMENT_GAME:
                fragment = new GameFragment();//游戏
                break;
            case FRAGMENT_SUBJECT:
                fragment = new SubjectFragment();//专题
                break;
            case FRAGMENT_RECOMMEND:
                fragment = new RecommendFragment();//推荐
                break;
            case FRAGMENT_CATEGORY:
                fragment = new CategoryFragment();//分类
                break;
            case FRAGMENT_HOT:
                fragment = new HotFragment();//排行
                break;
        }
        //保存到集合中
        fragmentMap.put(position,fragment);
        return  fragment;
    }
}
