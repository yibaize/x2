package org.baize.threedpool;

public interface ScheduleTask extends Runnable {
    ScheduleTaskTime getInitialDelay();

    ScheduleTaskTime getNextPeriod();

    boolean isPeriodChanged();

    boolean isCompleted();
}