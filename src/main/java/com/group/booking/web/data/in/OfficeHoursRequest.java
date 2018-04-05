package com.group.booking.web.data.in;

public class OfficeHoursRequest {
    private String start;
    private String end;

    public OfficeHoursRequest(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public OfficeHoursRequest() {
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
