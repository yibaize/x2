package org.baize.threedpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 作者： 白泽
 * 时间： 2018/1/6.
 * 描述：线程池拒绝策略
 */
public class MyRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (!executor.isShutdown()) {
            System.out.println("执行任务超时,runnable = " + r + ",Thread = " + Thread.currentThread().getName());
            r.run();
        }
    }
}
