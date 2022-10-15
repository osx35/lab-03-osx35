package org.example.scheduler.abstractions;

import java.time.Duration;

public interface IScheduleAnAction {
    void repeatEvery(Duration duration);

    void runImmediately();
}
