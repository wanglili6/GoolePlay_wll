package com.wll.googleplay_wll.proxy;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by 王丽丽 on 2017/3/3.
 * 线程池的代理工程
 * 主要是提供两个线程
 * 正常线程
 * 下载线程
 */

public class ThreadPoolFactory {
    /**
     * 由代理类创建两个线程
     */
    static ThreadPoolProxy mNomalThreadPoolExecutor;
    static ThreadPoolProxy mDownlodThreadPoolExecutor;

    /**
     * 获取正常的线程
     * @return
     */
    public static ThreadPoolProxy getmNomalThreadPoolExecutor() {
        synchronized (ThreadPoolFactory.class){
            if (mNomalThreadPoolExecutor==null){
               mNomalThreadPoolExecutor=new ThreadPoolProxy(5,5);
            }
        }
        return mNomalThreadPoolExecutor;
    }

    /**
     * 获取下载的线程
     * @return
     */
    public static ThreadPoolProxy getmDownlodThreadPoolExecutor() {
        synchronized (ThreadPoolFactory.class){
            if (mDownlodThreadPoolExecutor==null){
                mDownlodThreadPoolExecutor=new ThreadPoolProxy(3,3);
            }
        }
        return mDownlodThreadPoolExecutor;
    }
}
