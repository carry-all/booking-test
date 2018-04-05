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

    public String getEmployeeId() {
        return employeeId;
    }

    public String getMeetingStart() {
        return meetingStart;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setMeetingStart(String meetingStart) {
        this.meetingStart = meetingStart;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }
}
