package org.example.scheduler;

import org.example.scheduler.abstractions.IHandleErrors;
import org.example.scheduler.abstractions.IProvideNextExecutionTime;
import org.example.scheduler.abstractions.IRunNotSafeAction;

public class Scheduler {
    public static Scheduler getInstance() {
        return null;
    }

    public Scheduler forAction(IRunNotSafeAction randomlyThrowsAnError) {
        return null;
    }

    public Scheduler useExecutionTimeProvider(IProvideNextExecutionTime startsNowFor5SecondsMax5TimesWithDurationOf500Millis) {
        return null;
    }

    public Scheduler onError(IHandleErrors o) {
        return null;
    }

    public Scheduler onSingleActionCompleted(Runnable action) {
        return null;
    }

    public Scheduler onCompleted(Runnable action) {
        return null;
    }

    public void Schedule() {

    }

}
