package org.baize.threedpool;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 作者： 白泽
 * 时间： 2018/1/6.
 * 描述：
 */
public class SynchronousWorkTaskPoolImpl implements SynchronousWorkTaskPool {
    private ConcurrentLinkedQueue<Runnable> taskQueue = new ConcurrentLinkedQueue();
    private AtomicBoolean taskCompleted = new AtomicBoolean(true);
    private ThreadPoolExecutor executor;
    private SynchronousWorkTaskPoolImpl() {
    }

    public SynchronousWorkTaskPoolImpl(ThreadPoolExecutor executor) {
        this.executor = executor;
    }
//    private final GameReadWriteLock lock = GameReadWriteLock.createGameXpReadWriteLock();
//    private Runnable taskRunnable = new Runnable() {
//        public void run() {
//            while(true) {
//                try {
//                    SynchronousWorkTaskPoolImpl.this.lock.lockWrite();
//
//                    AbsWorkTask task;
//                    label88: {
//                        try {
//                            task = (AbsWorkTask)SynchronousWorkTaskPoolImpl.this.taskQueue.poll();
//                            if (task != null) {
//                                break label88;
//                            }
//
//                            SynchronousWorkTaskPoolImpl.this.taskCompleted.compareAndSet(false, true);
//                        } finally {
//                            SynchronousWorkTaskPoolImpl.this.lock.unlockWrite();
//                        }
//
//                        return;
//                    }
//
//                    task.run();
//                    SynchronousWorkTaskPoolImpl.this.lock.lockWrite();
//
//                    try {
//                        task.runEndNotify();
//                    } finally {
//                        SynchronousWorkTaskPoolImpl.this.lock.unlockWrite();
//                    }
//                } catch (Throwable var12) {
////                    LoggerService.getPlatformLog().error(var12.getMessage(), var12);
//                }
//            }
//        }
//    };



    public boolean isEmpty() {
        return this.taskQueue.isEmpty();
    }

    public boolean submit(Runnable work) {
//        try {
//            this.taskQueue.offer(work);
//            boolean submit = false;
//            this.lock.lockWrite();
//
//            try {
//                if (this.taskCompleted.get()) {
//                    this.taskCompleted.compareAndSet(true, false);
//                    submit = true;
//                }
//            } finally {
//                this.lock.unlockWrite();
//            }
//
//            if (submit) {
//                this.executor.execute(this.taskRunnable);
//            }
//
//            return true;
//        } catch (Throwable var7) {
////            LoggerService.getPlatformLog().error(var7.getMessage(), var7);
//            return false;
//        }
        return false;
    }

    public int getQueueSize() {
        return this.taskQueue.size();
    }

    public void clearQueue() {
        this.taskQueue.clear();
    }
}
