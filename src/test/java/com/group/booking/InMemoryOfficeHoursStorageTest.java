package com.group.booking;

import com.group.booking.data.Meeting;
import com.group.booking.data.OfficeHours;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class InMemoryOfficeHoursStorageTest {
    @Test
    public void meetingAtStart() {
        final OfficeHoursStorage storage = initStorage(9, 19);
        final LocalDateTime start = LocalDateTime.of(1999, 1, 1, 9, 0);
        final Meeting meeting = new Meeting(start, Duration.ofHours(1), "");

        final boolean result = storage.isMeetingWithinOfficeHours(meeting);

        assertThat(result, equalTo(true));
    }

    @Test
    public void meetingOneMinuteBeforeStart() {
        final OfficeHoursStorage storage = initStorage(9, 19);
        final LocalDateTime start = LocalDateTime.of(1999, 1, 1, 8, 59);
        final Meeting meeting = new Meeting(start, Duration.ofHours(1), "");

        final boolean result = storage.isMeetingWithinOfficeHours(meeting);

        assertThat(result, equalTo(false));
    }

    @Test
    public void meetingTooLong() {
        final OfficeHoursStorage storage = initStorage(9, 10);
        final LocalDateTime start = LocalDateTime.of(1999, 1, 1, 9, 0);
        final Meeting meeting = new Meeting(start, Duration.ofHours(1).plusMinutes(1), "");

        final boolean result = storage.isMeetingWithinOfficeHours(meeting);

        assertThat(result, equalTo(false));
    }

    private OfficeHoursStorage initStorage(int start, int end) {
        final OfficeHoursStorage storage = new InMemoryOfficeHoursStorage();

        final OfficeHours officeHours = new OfficeHours(Duration.ofHours(start), Duration.ofHours(end));
        storage.setOfficeHours(officeHours);

        return storage;
    }
}