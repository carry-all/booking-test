package com.group.booking.web.data.in;

import java.util.List;

public class BookingRequest {
    private OfficeHoursRequest officeHours;
    private List<BookingEntry> bookings;

    public BookingRequest(OfficeHoursRequest officeHours, List<BookingEntry> bookings) {
        this.officeHours = officeHours;
        this.bookings = bookings;
    }

    public BookingRequest() {
    }

    public OfficeHoursRequest getOfficeHours() {
        return officeHours;
    }

    public List<BookingEntry> getBookings() {
        return bookings;
    }

    public void setOfficeHours(OfficeHoursRequest officeHours) {
        this.officeHours = officeHours;
    }

    public void setBookings(List<BookingEntry> bookings) {
        this.bookings = bookings;
    }
}
