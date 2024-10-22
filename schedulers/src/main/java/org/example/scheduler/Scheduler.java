package org.example.scheduler;

import org.example.scheduler.abstractions.*;

import java.util.List;

public class Scheduler implements IScheduleWork {
    private static Scheduler instance;
    private List<IWork> jobs;

    private Scheduler(){}

    static{
        instance = new Scheduler();
    }

    static Scheduler builder(){return new Scheduler();}

    public static Scheduler getInstance(){return instance;}

    @Override
    public IWork forAction(IRunNotSafeAction action) {
        return new IWork() {
            private IProvideNextExecutionTime timeProvider;
            private IHandleErrors errorHandler;
            private IComplete onSingleActionCompleted;
            private IComplete onCompleted;

            @Override
            public IWork useExecutionTimeProvider(IProvideNextExecutionTime timeProvider) {
                this.timeProvider = timeProvider;
                return this;
            }

            @Override
            public IWork onError(IHandleErrors errorHandler) {
                this.errorHandler = errorHandler;
                return this;
            }

            @Override
            public IWork onSingleActionCompleted(IComplete onSingleActionCompleted) {
                this.onSingleActionCompleted = onSingleActionCompleted;
                return this;
            }

            @Override
            public IWork onCompleted(IComplete onCompleted) {
                this.onCompleted = onCompleted;
                return this;
            }

            @Override
            public void schedule() {
                if(timeProvider == null){
                    execute();
                }
            }

            @Override
            public void execute() {

                try{
                    action.executeNotSafeAction();

                    if(onSingleActionCompleted != null){
                        onSingleActionCompleted.complete();
                    }
                } catch (Exception e){
                    if(errorHandler != null){
                        errorHandler.handle(e);
                    }else {
                        throw new RuntimeException(e);
                    }
                } finally {
                    if(onCompleted != null){
                        onCompleted.complete();
                    }
                }

            }
        };
    }

    @Override
    public List<IWork> getJobs() {
        return jobs;
    }

    @Override
    public void addJob(IWork job) {
        jobs.add(job);
    }
}
