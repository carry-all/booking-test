package com.group.booking.data.transformers.web;

import com.group.booking.data.Meeting;
import com.group.booking.web.data.out.MeetingEntry;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MeetingToMeetingEntryConverter implements Converter<Meeting, MeetingEntry> {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public MeetingEntry convert(Meeting source) {
        final LocalDateTime start = source.getStart();
        final String startString = start.format(TIME_FORMAT);
        final LocalDateTime end = start.plus(source.getDuration());
        final String endString = end.format(TIME_FORMAT);
        return new MeetingEntry(startString, endString, source.getEmployeeId());
    }
}
