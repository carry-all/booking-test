package com.group.booking.web.data.out;

import java.util.List;

public class DayMeetingList {
    private final String date;
    private final List<MeetingEntry> meetings;

    public DayMeetingList(String date, List<MeetingEntry> meetings) {
        this.date = date;
        this.meetings = meetings;
    }

    public String getDate() {
        return date;
    }

    public List<MeetingEntry> getMeetings() {
        return meetings;
    }
}
