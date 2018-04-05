package com.group.booking.data;

import java.time.Duration;

public class OfficeHours {
    private final Duration start, end;

    public OfficeHours(Duration start, Duration end) {
        this.start = start;
        this.end = end;
    }

    public Duration getStart() {
        return start;
    }

    public Duration getEnd() {
        return end;
    }
}
