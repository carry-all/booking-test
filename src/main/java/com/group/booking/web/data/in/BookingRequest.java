package com.group.booking.web.data.in;

import java.util.List;

public class BookingRequest {
    private final OfficeHours officeHours;
    private final List<BookingEntry> bookings;

    public BookingRequest(OfficeHours officeHours, List<BookingEntry> bookings) {
        this.officeHours = officeHours;
        this.bookings = bookings;
    }

    public OfficeHours getOfficeHours() {
        return officeHours;
    }

    public List<BookingEntry> getBookings() {
        return bookings;
    }
}
