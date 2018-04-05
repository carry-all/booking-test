package com.group.booking.data.transformers.web;

import com.group.booking.data.OfficeHours;
import com.group.booking.web.data.in.OfficeHoursRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class OfficeHoursRequestToOfficeHoursConverter implements Converter<OfficeHoursRequest, OfficeHours> {
    private static final int HOUR_CLOCK_FORMAT_LENGTH = 4;

    private static Duration durationFromString(String timeString) {
        if (timeString == null || timeString.length() != HOUR_CLOCK_FORMAT_LENGTH) {
            throw new IllegalArgumentException(String.format("Only %s digit 24 hour clock format is accepted. Got '%s'", HOUR_CLOCK_FORMAT_LENGTH, timeString));
        }

        final String hourString = timeString.substring(0, 2);
        final String minString = timeString.substring(2);

        final Integer hours = Integer.valueOf(hourString);
        final Integer mins = Integer.valueOf(minString);

        if (hours < 0 || hours > 23) {
            throw new IllegalArgumentException(String.format("Bad hour value '%s'", hours));
        }

        if (mins < 0 || mins > 59) {
            throw new IllegalArgumentException(String.format("Bad minutes value '%s'", mins));
        }

        final Duration duration = Duration.ofHours(hours).plusMinutes(mins);

        return duration;
    }

    private static void complainIfStartNotBeforeEnd(Duration start, Duration end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("Start must be before end");
        }
    }

    @Override
    public OfficeHours convert(OfficeHoursRequest source) {
        final Duration start = durationFromString(source.getStart());
        final Duration end = durationFromString(source.getEnd());

        complainIfStartNotBeforeEnd(start, end);

        return new OfficeHours(start, end);
    }
}
