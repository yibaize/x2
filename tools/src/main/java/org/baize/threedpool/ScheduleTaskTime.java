package org.baize.threedpool;

import java.util.concurrent.TimeUnit;

public class ScheduleTaskTime {
    private long time;
    private TimeUnit timeUnit;

    public ScheduleTaskTime(long time, TimeUnit timeUnit) {
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public long getTime() {
        return this.time;
    }

    public TimeUnit getTimeUnit() {
        return this.timeUnit;
    }
}