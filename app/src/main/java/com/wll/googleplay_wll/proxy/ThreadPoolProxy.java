package com.wll.googleplay_wll.proxy;

/**
 * Created by 王丽丽 on 2017/3/3.
 */


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池代理类,替线程池完成相关操作
 * 1.代理模式就是多一个代理类出来，替原对象进行一些操作
 * 2.只需提供使用原对象时候真正关心的方法(提交任务,执行任务,移除任务)
 * 3.可以对原对象方法进行增强
 */

public class ThreadPoolProxy {
    private int mCorePoolSize;
    private int mMaximumPoolSize;
    private ThreadPoolExecutor mExecutor;

    /**
     * 构造方法
     *
     * @param mCorePoolSize
     * @param mMaximumPoolSize
     */
    public ThreadPoolProxy(int mCorePoolSize, int mMaximumPoolSize) {
        this.mCorePoolSize = mCorePoolSize;
        this.mMaximumPoolSize = mMaximumPoolSize;
    }

    /**
     * 初始化线程
     * <p>
     * 这里我们采用了懒汉式单例模式中常见的双重检查加锁方式来防止ThreadPool的重复创建。
     */
    public void initThreadPoolExector() {
        //判断是否为空--线程是否结束--线程是否执行完成--才创建线程对象
        if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()) {//懒汉式单例模式中常见的双重检查加锁方式
            long keepAliveTime = 0;//保持时间
            TimeUnit unit = TimeUnit.MILLISECONDS;//时间单位
            BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue();//任务队列
            ThreadFactory threadFactory = Executors.defaultThreadFactory();//线程工厂
            RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();//异常的捕捉器
            //线程池的默认类的实现
            mExecutor = new ThreadPoolExecutor(mCorePoolSize, mMaximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }


    }

    /**
     * 提交任务
     */
    public void submit(Runnable task) {
        initThreadPoolExector();
        mExecutor.submit(task);
    }

    /**
     * 执行任务
     */
    public void execute(Runnable task) {
        initThreadPoolExector();

        mExecutor.execute(task);
    }

    /**
     * 移除任务
     */
    public void remove(Runnable task) {
        initThreadPoolExector();

        mExecutor.remove(task);
    }

}
