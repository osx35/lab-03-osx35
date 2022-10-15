package org.example.scheduler.abstractions;

import java.time.LocalDateTime;

public interface IProvideNextExecutionTime {
    LocalDateTime getNextExecutionTime();
}
