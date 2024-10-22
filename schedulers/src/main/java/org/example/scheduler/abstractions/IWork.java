package org.example.scheduler.abstractions;

public interface IWork {
    IWork useExecutionTimeProvider(IProvideNextExecutionTime timeProvider);
    IWork onError(IHandleErrors errorHandler);
    IWork onSingleActionCompleted(IComplete onSingleActionCompleted);
    IWork onCompleted(IComplete onCompleted);
    void schedule();
    void execute();

}
