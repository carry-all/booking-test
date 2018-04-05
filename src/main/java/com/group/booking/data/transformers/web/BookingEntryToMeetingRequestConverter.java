package com.group.booking.data.transformers.web;

import com.group.booking.data.Meeting;
import com.group.booking.data.MeetingRequest;
import com.group.booking.data.transformers.web.raw.MeetingTimeConverter;
import com.group.booking.data.transformers.web.raw.RequestTimeConverter;
import com.group.booking.web.data.in.BookingEntry;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class BookingEntryToMeetingRequestConverter implements Converter<BookingEntry, MeetingRequest> {
    @Override
    public MeetingRequest convert(BookingEntry source) {
        final LocalDateTime requestTime = RequestTimeConverter.fromString(source.getRequestTime());
        final LocalDateTime start = MeetingTimeConverter.fromString(source.getMeetingStart());
        final Duration duration = Duration.ofHours(source.getDurationHours());

        final Meeting meeting = new Meeting(start, duration, source.getEmployeeId());
        return new MeetingRequest(requestTime, meeting);
    }
}
