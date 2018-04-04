package com.group.booking.web.data.in;

public class OfficeHours {
    private final String start;
    private final String end;

    public OfficeHours(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
