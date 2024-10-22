package org.example.scheduler.abstractions;

import java.util.List;

public interface IScheduleWork {
    IWork forAction(IRunNotSafeAction action);
    List<IWork> getJobs();
    void addJob(IWork job);
}
