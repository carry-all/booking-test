package com.group.booking.data.transformers.web;

import com.group.booking.data.Meeting;
import com.group.booking.web.data.out.MeetingEntry;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class MeetingToMeetingEntryConverterTest {

    private MeetingToMeetingEntryConverter converter = new MeetingToMeetingEntryConverter();

    @Test
    public void convert() {
        final LocalDateTime start = LocalDateTime.of(2000, 1, 1, 12, 30);
        final Meeting meeting = new Meeting(start, Duration.ofHours(3), "employee");

        final MeetingEntry out = converter.convert(meeting);

        assertThat(out.getStart(), equalTo("12:30"));
        assertThat(out.getEnd(), equalTo("15:30"));
        assertThat(out.getEmployeeId(), equalTo("employee"));
    }
}