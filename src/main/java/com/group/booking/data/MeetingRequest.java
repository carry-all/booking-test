package com.group.booking.data;

import java.time.LocalDateTime;

public class MeetingRequest {
    private final LocalDateTime requestTime;
    private final Meeting meeting;

    public MeetingRequest(LocalDateTime requestTime, Meeting meeting) {
        this.requestTime = requestTime;
        this.meeting = meeting;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public Meeting getMeeting() {
        return meeting;
    }
}
