package com.group.booking.data.transformers.web;

import com.group.booking.data.OfficeHours;
import com.group.booking.web.data.in.OfficeHoursRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Duration;

//todo: it's a strange converter. Why to request? Maybe response or web object?
@Component
public class OfficeHoursToOfficeRequestHoursConverter implements Converter<OfficeHours, OfficeHoursRequest> {

    private static final String STRING_TIME_FORMAT = "%02d%02d";
    private static final int MINUTES_IN_HOUR = 60;

    @Override
    public OfficeHoursRequest convert(OfficeHours source) {
        final String formattedStart = formatDurationAsString(source.getStart());
        final String formattedEnd = formatDurationAsString(source.getEnd());

        return new OfficeHoursRequest(formattedStart, formattedEnd);
    }

    private static String formatDurationAsString(Duration time) {
        final long hours = time.toHours();
        final long minutes = time.toMinutes() - hours * MINUTES_IN_HOUR;
        return String.format(STRING_TIME_FORMAT, hours, minutes);
    }
}
