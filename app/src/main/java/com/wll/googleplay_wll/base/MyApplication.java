package com.wll.googleplay_wll.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.socks.library.KLog;
import com.wll.googleplay_wll.conf.BuildConfig;

/**
 * Created by 王丽丽 on 2017/3/1.
 * 注意--不要忘记在清单文件中配置
 */

public class MyApplication extends Application {
    private static Context mContext;
    private static Handler mMainThreadHandler;
    private static int mMainThreadId;


    /**
     * 得到上下文
     * @return
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * 得到主线程里面的创建的一个hanlder
     * @return
     */
    public static Handler getmMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 得到主线程的id
     * @return
     */
    public static int getmMainThreadId() {
        return mMainThreadId;
    }

    @Override
    public void onCreate() {//程序的入口方法
        //上下文
        mContext = getApplicationContext();

        //主线程的Handler
        mMainThreadHandler = new Handler();

        //主线程的线程id
        mMainThreadId = android.os.Process.myTid();
        /**
         myTid:Thread
         myPid:Process
         myUid:User
         */
        super.onCreate();
        KLog.init(BuildConfig.LOG_DEBUG, "wllz");

    }
}
