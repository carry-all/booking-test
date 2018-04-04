package com.group.booking.web.data.out;

public class MeetingEntry {
    private final String start;
    private final String end;
    private final String employeeId;

    public MeetingEntry(String start, String end, String employeeId) {
        this.start = start;
        this.end = end;
        this.employeeId = employeeId;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getEmployeeId() {
        return employeeId;
    }
}
