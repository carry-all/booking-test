package com.group.booking;

import com.group.booking.data.Meeting;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InMemoryMeetingsStorageTest {
    final InMemoryMeetingsStorage storage = new InMemoryMeetingsStorage();

    @Test
    public void meetingAllowedWhenEmpty() {
        final LocalDateTime start = LocalDateTime.of(2000, 1, 1, 10, 15);
        final Meeting meeting = new Meeting(start, Duration.ofHours(2), "none");

        assertThat(storage.isMeetingAllowed(meeting), is(true));
    }

    @Test
    public void addedMeetingIsntAllowedOneMoreTime() {
        final LocalDateTime start = LocalDateTime.of(2000, 1, 1, 10, 15);
        final Meeting meeting = new Meeting(start, Duration.ofHours(2), "none");

        storage.addMeeting(meeting);

        assertThat(storage.isMeetingAllowed(meeting), is(false));
    }

    @Test
    public void touchingMeetingsAreAllowed() {
        final LocalDateTime start1 = LocalDateTime.of(2000, 1, 1, 10, 0);
        final Meeting meeting1 = new Meeting(start1, Duration.ofHours(2), "none");
        final LocalDateTime start2 = LocalDateTime.of(2000, 1, 1, 12, 0);
        final Meeting meeting2 = new Meeting(start2, Duration.ofHours(2), "none");

        storage.addMeeting(meeting1);

        assertThat(storage.isMeetingAllowed(meeting2), is(true));
    }

    @Test
    public void oneMinuteOverlappingMeetingsArentAllowed() {
        final LocalDateTime start1 = LocalDateTime.of(2000, 1, 1, 10, 0);
        final Meeting meeting1 = new Meeting(start1, Duration.ofHours(2).plusMinutes(1), "none");
        final LocalDateTime start2 = LocalDateTime.of(2000, 1, 1, 12, 0);
        final Meeting meeting2 = new Meeting(start2, Duration.ofHours(2), "none");

        storage.addMeeting(meeting1);

        assertThat(storage.isMeetingAllowed(meeting2), is(false));
    }

    @Test
    public void tinyMeetingInTheStartOfHugeMeetingIsntAllowed() {
        final LocalDateTime start1 = LocalDateTime.of(2000, 1, 1, 10, 0);
        final Meeting meeting1 = new Meeting(start1, Duration.ofHours(8), "none");
        final LocalDateTime start2 = LocalDateTime.of(2000, 1, 1, 10, 30);
        final Meeting meeting2 = new Meeting(start2, Duration.ofMinutes(1), "none");

        storage.addMeeting(meeting1);

        assertThat(storage.isMeetingAllowed(meeting2), is(false));
    }

    @Test
    public void hugeMeetingOnTopOfSmallMeetingIsntAllowed() {
        final LocalDateTime start1 = LocalDateTime.of(2000, 1, 1, 13, 0);
        final Meeting meeting1 = new Meeting(start1, Duration.ofMinutes(1), "none");
        final LocalDateTime start2 = LocalDateTime.of(2000, 1, 1, 10, 30);
        final Meeting meeting2 = new Meeting(start2, Duration.ofHours(10), "none");

        storage.addMeeting(meeting1);

        assertThat(storage.isMeetingAllowed(meeting2), is(false));
    }
}