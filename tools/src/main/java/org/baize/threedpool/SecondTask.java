package org.baize.threedpool;

import java.util.concurrent.TimeUnit;

public abstract class SecondTask implements ScheduleTask {
    private final ScheduleTaskTime initialDelay;
    private final ScheduleTaskTime nextPeriod;

    public SecondTask(int seconds) {
        this.initialDelay = new ScheduleTaskTime((long)seconds, TimeUnit.SECONDS);
        this.nextPeriod = new ScheduleTaskTime((long)seconds, TimeUnit.SECONDS);
    }

    public final ScheduleTaskTime getInitialDelay() {
        return this.initialDelay;
    }

    public final ScheduleTaskTime getNextPeriod() {
        return this.nextPeriod;
    }

    public final boolean isPeriodChanged() {
        return false;
    }
}