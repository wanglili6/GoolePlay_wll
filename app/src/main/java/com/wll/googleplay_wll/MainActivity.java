package com.wll.googleplay_wll;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.astuetz.PagerSlidingTabStrip;
import com.astuetz.PagerSlidingTabStripExtends;
import com.wll.googleplay_wll.adapter.MainViewpagerAdapter;
import com.wll.googleplay_wll.base.BaseFragment;
import com.wll.googleplay_wll.base.LoadingPager;
import com.wll.googleplay_wll.factory.FragmentFactory;
import com.wll.googleplay_wll.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl_left)
    FrameLayout flLeft;
    @BindView(R.id.fl_contects)
    FrameLayout flContects;
    @BindView(R.id.main_drawlayout)
    DrawerLayout mainDrawlayout;
    @BindView(R.id.main_tabs)
    PagerSlidingTabStripExtends mainTabs;
    @BindView(R.id.main_vp)
    ViewPager mainVp;
    private ActionBarDrawerToggle mToogle;
    public String[] titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initActionBar();
        initActionBarDrawTogger();

        titles = UIUtils.getStrings(R.array.main_titles);
        initViewPager();
        initLIstenter();

    }


    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        mainVp.setAdapter(new MainViewpagerAdapter(getSupportFragmentManager(), titles));
        //绑定ViewPager
        mainTabs.setViewPager(mainVp);
    }


    /**
     * 初始化actionBar
     */
    private void initActionBar() {
        //获取ActionBar的实例
        ActionBar actionBar = getSupportActionBar();

        //设置标题
        actionBar.setTitle("谷歌电子市场");
        actionBar.setSubtitle("wll");
        //设置图标
        actionBar.setIcon(R.drawable.ic_launcher);
        actionBar.setLogo(R.drawable.ic_action_call);

        ///显示logo/icon(图标)，默认是false,默认是隐藏图标
        actionBar.setDisplayShowHomeEnabled(false);

        //修改icon和logo显示的优先级，默认是false,默认是没用logo,用的icon
        actionBar.setDisplayUseLogoEnabled(true);

        //显示回退部分，默认是false,默认隐藏了回退部分
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 初始化侧滑菜单的按钮
     */
    private void initActionBarDrawTogger() {
        mToogle = new ActionBarDrawerToggle(this, mainDrawlayout, R.string.open, R.string.close);

        //设置同步方法---修改Ui
        mToogle.syncState();
        //设置drawerLayout的监听 --> DrawerLayout拖动的时候,toggle可以跟着改变ui
        mainDrawlayout.setDrawerListener(mToogle);

    }

    /**
     * 设置actionBar的监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mToogle.onOptionsItemSelected(item);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private void initLIstenter() {
        //设置ViewPager的监听
        final MyOnPageChangeListener myOnPageChangeListener = new MyOnPageChangeListener();
        mainTabs.setOnPageChangeListener(myOnPageChangeListener);


        //获取ViewPager的资源树----LoadingPager首页未加载
        mainVp.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //ViewPager已经展示给用户看-->说明HomeFragment和AppFragment已经创建好了
                //手动选中第一页，触发加载数据的方法
                myOnPageChangeListener.onPageSelected(0);
                mainVp.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

    }

    /**
     * 监听器
     */

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //根据索引获取对应的fragment
            BaseFragment baseFragment = FragmentFactory.fragmentMap.get(position);

            //拿到对应loadingpage
            LoadingPager loadingPager = baseFragment.getmLoaddingPage();
            //加载数据
            loadingPager.triggerLoadData();

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
