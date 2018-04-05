package com.group.booking.web.data.in;

public class BookingEntry {
    private String requestTime;
    private String employeeId;
    private String meetingStart;
    private int durationHours;

    public BookingEntry(String requestTime, String employeeId, String meetingStart, int durationHours) {
        this.requestTime = requestTime;
        this.employeeId = employeeId;
        this.meetingStart = meetingStart;
        this.durationHours = durationHours;
    }

    public BookingEntry() {
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getMeetingStart() {
        return meetingStart;
    }

    public void setMeetingStart(String meetingStart) {
        this.meetingStart = meetingStart;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }
}
