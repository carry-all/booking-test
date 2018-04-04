package com.group.booking.web.data.out;

import java.util.List;

public class BookingResponse {
    private final List<DayMeetingList> days;

    public BookingResponse(List<DayMeetingList> days) {
        this.days = days;
    }

    public List<DayMeetingList> getDays() {
        return days;
    }
}
