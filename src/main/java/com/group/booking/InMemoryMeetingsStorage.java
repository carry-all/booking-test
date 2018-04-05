package com.group.booking;

import com.group.booking.data.Meeting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class InMemoryMeetingsStorage implements MeetingsStorage {
    private final TreeSet<Meeting> meetings = new TreeSet<>(Comparator.comparing(Meeting::getStart));

    @Override
    public boolean isMeetingAllowed(Meeting meeting) {
        final LocalDateTime theMeetingStart = meeting.getStart();
        final LocalDate meetingDate = theMeetingStart.toLocalDate();
        final List<Meeting> otherMeetings = getMeetingsByDate(meetingDate);

        final LocalDateTime theMeetingMiddle = theMeetingStart.plus(meeting.getDuration().dividedBy(2));
        final LocalDateTime theMeetingEnd = theMeetingStart.plus(meeting.getDuration());

        final boolean conflictDetected = otherMeetings.stream().anyMatch(otherMeetingToCheck -> {
            final LocalDateTime otherMeetingStart = otherMeetingToCheck.getStart();
            final LocalDateTime otherMeetingEnd = otherMeetingStart.plus(otherMeetingToCheck.getDuration());

            final boolean meetingStartIsInsideOfOther = isDateInsideInterval(theMeetingStart, otherMeetingStart, otherMeetingEnd);
            final boolean meetingMiddleIsInsideOfOther = isDateInsideInterval(theMeetingMiddle, otherMeetingStart, otherMeetingEnd);
            final boolean meetingEndIsInsideOfOther = isDateInsideInterval(theMeetingEnd, otherMeetingStart, otherMeetingEnd);
            final boolean otherMeetingStartIsInsideOfTheMeeting = isDateInsideInterval(otherMeetingStart, theMeetingStart, theMeetingEnd);

            return meetingStartIsInsideOfOther || meetingMiddleIsInsideOfOther || meetingEndIsInsideOfOther || otherMeetingStartIsInsideOfTheMeeting;
        });

        return !conflictDetected;
    }

    private boolean isDateInsideInterval(LocalDateTime theDate, LocalDateTime intervalStart, LocalDateTime intervalEnd) {
        return theDate.isAfter(intervalStart) && theDate.isBefore(intervalEnd);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        //todo: protection, syncrhonization
        meetings.add(meeting);
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return new ArrayList<>(meetings);
    }

    @Override
    public List<Meeting> getMeetingsByDate(LocalDate date) {
        // todo: where is split day protection
        return meetings.stream().filter(meeting -> meeting.getStart().toLocalDate().equals(date)).collect(Collectors.toList());
    }
}
