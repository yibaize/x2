package org.baize.maseegehandler;

import org.baize.model.JdbcModel;
import org.baize.model.JdbcOperation;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 作者： 白泽
 * 时间： 2017/12/26.
 * 描述：
 */
public class JdbcTemplate {
    private final ScheduledExecutorService timer;
    private final JdbcOperation operation;
    private final ConcurrentLinkedQueue<JdbcModel> taskQueue;
    private final AtomicBoolean taskCompleted = new AtomicBoolean(false);
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public JdbcTemplate(JdbcModel model) {
        operation = new JdbcOperation(model);
        taskQueue = new ConcurrentLinkedQueue<>();
        this.timer = Executors.newScheduledThreadPool(1);
    }
    public void commit(JdbcModel tast){
        taskQueue.offer(tast);
        boolean submit = false;
        lock.writeLock().lock();
        try {
            if(taskCompleted.get());{
                taskCompleted.compareAndSet(true,false);
                submit = true;
            }
        }finally {
            lock.writeLock().unlock();
        }
        //提交线程
        if(submit)
            update();
    }
    private void update(){
        if(taskQueue.size() <= 0)
            return;
        timer.scheduleAtFixedRate(() -> {
            while (true) {
                lock.writeLock().lock();
                JdbcModel msg = null;
                OK:
                {
                    try {
                        msg = taskQueue.poll();
                        if (msg != null)
                            break OK;
                        taskCompleted.compareAndSet(false, true);
                    } finally {
                        lock.writeLock().unlock();
                    }
                    return;//防止线程挂起阻塞
                }
                operation.inset(msg);
            }
        }, 0,3L, TimeUnit.MINUTES);
    }
    public void insert(JdbcModel model){
        operation.inset(model);
    }
    public JdbcModel select(JdbcModel model){
        return operation.select(model);
    }
//    public List<JdbcModel> selectAll(){
//        operation.
//    }
    public void delete(JdbcModel model){
        operation.delete(model);
    }
}
