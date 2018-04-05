package com.group.booking.data.transformers.web.raw;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class RequestTimeConverterTest {
    @Test
    public void fromString() {
        final LocalDateTime time = RequestTimeConverter.fromString("2011-03-17 20:17:06");

        assertThat(time.getYear(), equalTo(2011));
        assertThat(time.getMonthValue(), equalTo(3));
        assertThat(time.getDayOfMonth(), equalTo(17));
        assertThat(time.getHour(), equalTo(20));
        assertThat(time.getMinute(), equalTo(17));
        assertThat(time.getSecond(), equalTo(6));
    }

    @Test
    public void inString() {
        final LocalDateTime in = LocalDateTime.of(1999, 12, 31, 23, 59, 59);
        final String result = RequestTimeConverter.toWeb(in);

        assertThat(result, equalTo("1999-12-31 23:59:59"));
    }
}