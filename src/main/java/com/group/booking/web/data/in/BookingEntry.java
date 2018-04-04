package com.group.booking.web.data.in;

public class BookingEntry {
    private final String requestTime;
    private final String employeeId;
    private final String meetingStart;
    private final int durationHours;

    public BookingEntry(String requestTime, String employeeId, String meetingStart, int durationHours) {
        this.requestTime = requestTime;
        this.employeeId = employeeId;
        this.meetingStart = meetingStart;
        this.durationHours = durationHours;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getMeetingStart() {
        return meetingStart;
    }

    public int getDurationHours() {
        return durationHours;
    }
}
