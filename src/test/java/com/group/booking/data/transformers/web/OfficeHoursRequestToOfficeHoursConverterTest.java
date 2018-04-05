package com.group.booking.data.transformers.web;

import com.group.booking.web.data.in.OfficeHoursRequest;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class OfficeHoursRequestToOfficeHoursConverterTest {
    final OfficeHoursRequestToOfficeHoursConverter converter = new OfficeHoursRequestToOfficeHoursConverter();

    @Test
    public void normalValues() {
        testFromWebConversion("1000", "1900", "PT10H", "PT19H");
    }

    @Test
    public void valuesWithMins() {
        testFromWebConversion("0003", "2359", "PT3M", "PT23H59M");
    }

    private void testFromWebConversion(String startStr, String endStr, String startCode, String endCode) {
        final OfficeHoursRequest in = new OfficeHoursRequest(startStr, endStr);
        final com.group.booking.data.OfficeHours out = converter.convert(in);

        assertThat(out.getStart().toString(), equalTo(startCode));
        assertThat(out.getEnd().toString(), equalTo(endCode));
    }

}