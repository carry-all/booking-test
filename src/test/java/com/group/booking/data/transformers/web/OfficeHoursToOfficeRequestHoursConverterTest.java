package com.group.booking.data.transformers.web;

import com.group.booking.web.data.in.OfficeHoursRequest;
import org.junit.Test;

import java.time.Duration;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class OfficeHoursToOfficeRequestHoursConverterTest {
    OfficeHoursToOfficeRequestHoursConverter converter = new OfficeHoursToOfficeRequestHoursConverter();

    @Test
    public void toWebConversion() {
        final com.group.booking.data.OfficeHours in
                = new com.group.booking.data.OfficeHours(Duration.ofHours(0), Duration.ofHours(20).plusMinutes(30));
        final OfficeHoursRequest out = converter.convert(in);

        assertThat(out.getStart(), equalTo("0000"));
        assertThat(out.getEnd(), equalTo("2030"));
    }
}