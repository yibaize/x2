package org.baize.threedpool;

import java.util.concurrent.Future;

/**
 * 作者： 白泽
 * 时间： 2018/1/6.
 * 描述：
 */
public interface WorkTaskPoolManager {
    /**
     * 创建线程池
     * @param poolName 线程池名称
     * @param corePoolSize 核心线程池大小
     * @param maximumPoolSize 线程池最大容量大小
     * @param keepAliveTime 线程池空闲时，线程存活的时间
     */
    void  start(String poolName,int corePoolSize ,int maximumPoolSize ,int keepAliveTime);
    Future<?> submitAndExecute(Runnable var1);
    //SynchronousWorkTaskPool createSynchronousWorkTaskPool();
}
