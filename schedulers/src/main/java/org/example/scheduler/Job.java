package org.example.scheduler;

import org.example.scheduler.abstractions.*;

public class Job implements IWork {
    private IRunNotSafeAction action;
    private IProvideNextExecutionTime nextTimeProvider = ()->null;
    private IHandleErrors handleExceptions = ex -> {};
    private IComplete singleActionCompleted = ()->{};
    private IComplete completed=()->{};
    private IScheduleWork scheduler;

    public Job(IRunNotSafeAction action, IScheduleWork scheduler) {
        this.action = action;
        this.scheduler = scheduler;
    }

    @Override
    public IWork useExecutionTimeProvider(IProvideNextExecutionTime timeProvider) {
        return null;
    }

    @Override
    public IWork onError(IHandleErrors errorHandler) {
        return null;
    }

    @Override
    public IWork onSingleActionCompleted(IComplete onSingleActionCompleted) {
        return null;
    }

    @Override
    public IWork onCompleted(IComplete onCompleted) {
        return null;
    }

    @Override
    public void schedule() {
        scheduler.addJob(this);
    }

    @Override
    public void execute() {
        SchedulerThread thread = new SchedulerThread();
        thread.run();
    }
}
