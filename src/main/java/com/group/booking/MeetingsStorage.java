package com.group.booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MeetingsStorage {
    boolean isMeetingAllowed(Meeting meeting);
    void addMeeting(Meeting meeting);
    void setWorkingHours(LocalTime start, LocalTime end);
    List<Meeting> getAllMeetings();
    List<Meeting> getMeetingsByDate(LocalDate date);
}
