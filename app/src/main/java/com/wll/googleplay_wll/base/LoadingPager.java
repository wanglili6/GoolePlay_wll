package com.wll.googleplay_wll.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.wll.googleplay_wll.R;
import com.wll.googleplay_wll.utils.UIUtils;

/**
 * Created by 王丽丽 on 2017/3/2.
 * 加载视图和加载数据相关联
 * <p>
 * 1.提供视图-->4种视图中的一种(加载中视图,错误视图,空视图,成功视图)-->把自身提供出去就可以
 * 2.加载数据
 * 3.数据和视图的绑定
 */

public abstract class LoadingPager extends FrameLayout {

    /**
     * 创建状态值
     */
    public static final int STATE_LOADING = 0;//加载中
    public static final int STATE_ERROR = 1;//错误
    public static final int STATE_SUCCESS = 2;//成功
    public static final int STATE_EMPTY = 3;//空
    public int mCurState = STATE_LOADING;//默认是加载中的情况


    private View entryView;
    private View errorVIew;
    private View loadingView;
    private View mSuccessView;
    private LoadDataTask target;

    public LoadingPager(Context context) {
        super(context);
        initViewCommon();
    }

    /**
     * 初始化视图
     */
    private void initViewCommon() {
        //空视图---添加到容器里
        entryView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.pager_empty, null);
        this.addView(entryView);
        //错误视图----添加到容器里
        errorVIew = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.pager_error, null);
        this.addView(errorVIew);


        //设置空视图按钮的点击事件
        //找到错误视图里面重试按钮,设置点击事件
        errorVIew.findViewById(R.id.error_btn_retry).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新触发加载数据
                triggerLoadData();
            }
        });

        //加载中....添加到容器里
        loadingView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.pager_loading, null);
        this.addView(loadingView);

        //因为成功视图的布局不确定---所以先不创建成功视图


        //根据当前页面状态刷新UI
        refreshViewByState();

    }

    private void refreshViewByState() {
        //空布局
        if (mCurState == STATE_EMPTY) {
            entryView.setVisibility(View.VISIBLE);
        } else {
            entryView.setVisibility(View.GONE);
        }
//加载中
        if (mCurState == STATE_LOADING) {
            loadingView.setVisibility(View.VISIBLE);
        } else {
            loadingView.setVisibility(View.GONE);
        }
        //错误视图

        if (mCurState == STATE_ERROR) {
            errorVIew.setVisibility(View.VISIBLE);
        } else {
            errorVIew.setVisibility(View.GONE);
        }

        //动态加载成功视图
        if (mSuccessView == null&&mCurState==STATE_SUCCESS) {
            mSuccessView = initSuccess();
           this.addView(mSuccessView);
        }
        if (mSuccessView != null) {
           if (mCurState==STATE_SUCCESS){
               mSuccessView.setVisibility(View.VISIBLE);
           }else {
               mSuccessView.setVisibility(View.GONE);
           }
        }

    }

    /**
     * 创建成功视图
     * @return
     */
    public abstract View initSuccess();

    /**
     *触发加载数据
     */
    public void triggerLoadData() {
        if (mCurState!=STATE_SUCCESS){//解决加载成功再次重新加载的问题

            if (target==null){//解决加载中无需再次加载

                // 控制数据加载之前显示加载中的视图
                mCurState = STATE_LOADING;//开始加载数据的时候应该显示加载中视图
                //刷新Ui
                refreshViewByState();


                target = new LoadDataTask();
                new Thread(target).start();
            }

        }

    }

    public class LoadDataTask implements Runnable {

        @Override
        public void run() {
            //得到具体数据
            LoadedResult  loadedResult = initData();
            //拿到状态---
            mCurState = loadedResult.getState();

            //在主线程刷新UI
            MyApplication.getmMainThreadHandler().post(new Runnable() {
                @Override
                public void run() {
                    //刷新Ui
                    refreshViewByState();
                }
            });

            //置空任务===加载中无需再次加载
            target=null;

        }
    }

    /**
     * 获取真实数据
     * @return
     */
    protected abstract LoadedResult initData();

    /**
            *  标识数据加载结果的枚举类
    */
    public enum LoadedResult {
        /**
         * STATE_ERROR = 1;//错误
         * STATE_SUCCESS = 2;//成功
         * STATE_EMPTY = 3;//空
         */
        SUCCESS(STATE_SUCCESS), ERROR(STATE_ERROR), EMPTY(STATE_EMPTY);

        private int state;

        public int getState() {
            return state;
        }

        LoadedResult(int state) {
            this.state = state;
        }
    }


}
