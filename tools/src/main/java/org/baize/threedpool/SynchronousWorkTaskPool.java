package org.baize.threedpool;

/**
 * 作者： 白泽
 * 时间： 2018/1/6.
 * 描述：
 */
public interface SynchronousWorkTaskPool {
    boolean isEmpty();

    int getQueueSize();

    void clearQueue();

    boolean submit(Runnable task);
}
