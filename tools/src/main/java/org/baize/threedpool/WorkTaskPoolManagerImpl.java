package org.baize.threedpool;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 作者： 白泽
 * 时间： 2018/1/6.
 * 描述：
 */
public class WorkTaskPoolManagerImpl implements WorkTaskPoolManager{
    private static final WorkTaskPoolManager istance = new WorkTaskPoolManagerImpl();
    private AtomicBoolean isStart = new AtomicBoolean(false);
    private ThreadPoolExecutor executor;
    @Override
    public void start(String poolName, int corePoolSize, int maximumPoolSize, int keepAliveTime) {
        boolean compareAndSet = this.isStart.compareAndSet(false, true);
        if(!compareAndSet){
            System.out.println("线程已经启动");
            return;
        }
        this.executor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                (long)keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new MyThreadFactory(poolName),
                new MyRejectedExecutionHandler());
        System.out.println("------------------- 工作任务池 初始化成功 -------------------");
    }

    @Override
    public Future<?> submitAndExecute(Runnable var1) {
        return null;
    }
}
