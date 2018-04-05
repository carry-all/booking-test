package com.group.booking.data.transformers.web.raw;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * YYYY-MM-DD HH:MM
 */
public class MeetingTimeConverter {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm")
            .toFormatter();

    public static LocalDateTime fromString(String strTime) {
        return LocalDateTime.parse(strTime, DATE_TIME_FORMATTER);
    }

    public static String toWeb(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }
}
