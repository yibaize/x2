package org.baize.threedpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 作者： 白泽
 * 时间： 2018/1/6.
 * 描述：
 */
public class MyThreadFactory implements ThreadFactory{
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup threadGroup;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String poolName;

    public MyThreadFactory(String poolName) {
        SecurityManager s = System.getSecurityManager();
        this.threadGroup = s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.poolName = poolName+ " [" + poolNumber.getAndIncrement() + " ] - threed - ";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(this.threadGroup,r,this.poolName+this.threadNumber.getAndIncrement(),0L);
        //如果是守护线程，设置为不是守护线程
        if(t.isDaemon())
            t.setDaemon(false);
        //如果优先级不等于5，设置为5
        if(t.getPriority() != 5)
            t.setPriority(5);
        return t;
    }
}
