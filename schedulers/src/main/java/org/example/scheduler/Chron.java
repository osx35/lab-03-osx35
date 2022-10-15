package org.example.scheduler;

import org.example.scheduler.abstractions.IProvideNextExecutionTime;

import java.time.Duration;
import java.time.LocalDateTime;

public class Chron {
    public static Chron builder() {
        return new Chron();
    }

    public Chron setStartTime(LocalDateTime localDateTime) {
        return this;
    }

    public Chron setEndDate(LocalDateTime localDateTime) {
        return this;
    }

    public Chron setMaxExecutionTimes(int count) {
        return this;
    }

    public Chron setIntervalDuration(Duration duration) {
        return this;
    }

    public IProvideNextExecutionTime buildNextTimeExecutionProvider() {
        return () -> LocalDateTime.now();
    }
}
