package org.example;

import org.example.scheduler.abstractions.IProvideNextExecutionTime;

import java.time.Duration;
import java.time.LocalDateTime;

public class Chron {
    private LocalDateTime startTime;
    private LocalDateTime endDate;
    private int maxExecutionTimes;
    private Duration intervalDuration;
    private int executionCounter;

    static Chron builder(){
        return new Chron();
    }

    public Chron setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public Chron setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public Chron setMaxExecutionTimes(int maxExecutionTimes) {
        this.maxExecutionTimes = maxExecutionTimes;
        return this;
    }

    public Chron setIntervalDuration(Duration intervalDuration) {
        this.intervalDuration = intervalDuration;
        return this;
    }
    private LocalDateTime getNextTime() {
        this.executionCounter++;
        if(this.executionCounter > this.maxExecutionTimes) {
            return null;
        }
        return startTime.plus(intervalDuration);
    }

    public IProvideNextExecutionTime buildNextTimeExecutionProvider() {
        return ()->getNextTime();
    }
}
