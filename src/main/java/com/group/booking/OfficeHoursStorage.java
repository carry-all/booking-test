package com.group.booking;

import com.group.booking.data.Meeting;
import com.group.booking.data.OfficeHours;

public interface OfficeHoursStorage {
    boolean isOfficeHoursSet();
    void setOfficeHours(OfficeHours officeHours);
    boolean isMeetingWithinOfficeHours(Meeting meeting);
}
