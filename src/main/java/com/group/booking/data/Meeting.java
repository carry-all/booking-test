package com.group.booking.data;

import java.time.Duration;
import java.time.LocalDateTime;

public class Meeting {
    private final LocalDateTime start;
    private final Duration duration;
    private final String employeeId;

    public Meeting(LocalDateTime start, Duration duration, String employeeId) {
        if (duration.isZero() || duration.isNegative()) {
            throw new IllegalArgumentException();
        }

        this.start = start;
        this.duration = duration;
        this.employeeId = employeeId;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getEmployeeId() {
        return employeeId;
    }
}
