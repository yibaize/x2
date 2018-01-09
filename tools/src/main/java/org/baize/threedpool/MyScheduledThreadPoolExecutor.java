package org.baize.threedpool;

import org.baize.LoggerUtils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public final class MyScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
    private static final AtomicLong sequencer = new AtomicLong();
    private static final long NANO_ORIGIN = System.nanoTime();

    MyScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory factory) {
        super(corePoolSize, factory);
    }

    void submit(ScheduleTask scheduleTask, long initialDelay, TimeUnit unit) {
        if (scheduleTask != null && unit != null) {
            if (initialDelay <= 0L) {
                initialDelay = 0L;
            } else {
                initialDelay = unit.toNanos(initialDelay);
            }

            long triggerTime = this.now() + initialDelay;
            RunnableScheduledFuture<Object> t = new MyScheduledThreadPoolExecutor.ScheduledFutureTask(scheduleTask, triggerTime, scheduleTask.getNextPeriod().getTimeUnit().toNanos(scheduleTask.getNextPeriod().getTime()));
            if (this.isShutdown()) {
                this.shutdownNow();
                this.getRejectedExecutionHandler().rejectedExecution(t, this);
            } else {
                if (this.getPoolSize() < this.getCorePoolSize()) {
                    this.prestartCoreThread();
                }

                super.getQueue().add(t);
            }
        } else {
            throw new NullPointerException();
        }
    }

    private long now() {
        return System.nanoTime() - NANO_ORIGIN;
    }

    static class ScheduleTaskCallable implements Callable {
        private final ScheduleTask task;

        ScheduleTaskCallable(ScheduleTask task) {
            this.task = task;
        }

        public Object call() {
            try {
                this.task.run();
            } catch (Throwable var2) {
                LoggerUtils.getPlatformLog().error(var2.getMessage(), var2);
            }

            return null;
        }
    }

    private class ScheduledFutureTask<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {
        private long time;
        private final long sequenceNumber;
        private final ScheduleTask task;
        private volatile long period;

        ScheduledFutureTask(ScheduleTask task, long ns, long period) {
            super(new MyScheduledThreadPoolExecutor.ScheduleTaskCallable(task));
            this.task = task;
            this.time = ns;
            this.period = period;
            this.sequenceNumber = MyScheduledThreadPoolExecutor.sequencer.getAndIncrement();
        }

        public long getDelay(TimeUnit unit) {
            long d = unit.convert(this.time - MyScheduledThreadPoolExecutor.this.now(), TimeUnit.NANOSECONDS);
            return d;
        }

        public int compareTo(Delayed other) {
            if (other == this) {
                return 0;
            } else if (other instanceof MyScheduledThreadPoolExecutor.ScheduledFutureTask) {
                MyScheduledThreadPoolExecutor.ScheduledFutureTask<?> x = (MyScheduledThreadPoolExecutor.ScheduledFutureTask)other;
                long diff = this.time - x.time;
                if (diff < 0L) {
                    return -1;
                } else if (diff > 0L) {
                    return 1;
                } else {
                    return this.sequenceNumber < x.sequenceNumber ? -1 : 1;
                }
            } else {
                long d = this.getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS);
                return d == 0L ? 0 : (d < 0L ? -1 : 1);
            }
        }

        public boolean isPeriodic() {
            return this.period != 0L;
        }

        private void runPeriodic() {
            boolean ok = false;//access$201(this);
            boolean down = MyScheduledThreadPoolExecutor.this.isShutdown();
            if (ok && (!down || MyScheduledThreadPoolExecutor.this.getContinueExistingPeriodicTasksAfterShutdownPolicy() && !MyScheduledThreadPoolExecutor.this.isTerminating())) {
                if (this.task.isCompleted()) {
                    return;
                }

                if (this.task.isPeriodChanged()) {
                    this.period = this.task.getNextPeriod().getTimeUnit().toNanos(this.task.getNextPeriod().getTime());
                }

                long p = this.period;
                if (p > 0L) {
                    this.time += p;
                } else {
                    this.time = MyScheduledThreadPoolExecutor.this.now() - p;
                }

                MyScheduledThreadPoolExecutor.super.getQueue().add(this);
            }

        }

        public long getPeriod() {
            return this.task.getNextPeriod().getTimeUnit().toNanos(this.task.getNextPeriod().getTime());
        }

        public void run() {
            if (this.isPeriodic()) {
                this.runPeriodic();
            } else {
//                access$401(this);
            }

        }
    }
}