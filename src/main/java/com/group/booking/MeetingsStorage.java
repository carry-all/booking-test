package com.group.booking;

import com.group.booking.data.Meeting;

import java.time.LocalDate;
import java.util.List;

public interface MeetingsStorage {
    boolean isMeetingAllowed(Meeting meeting);
    void addMeeting(Meeting meeting);
    List<Meeting> getAllMeetings();
    List<Meeting> getMeetingsByDate(LocalDate date);
}
