package com.group.booking;

import com.group.booking.data.Meeting;
import com.group.booking.data.OfficeHours;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class InMemoryOfficeHoursStorage implements OfficeHoursStorage {
    private Optional<OfficeHours> officeHours = Optional.empty();

    @Override
    public boolean isOfficeHoursSet() {
        return officeHours.isPresent();
    }

    @Override
    public void setOfficeHours(OfficeHours officeHours) {
        this.officeHours = Optional.of(officeHours);
    }

    @Override
    public boolean isMeetingWithinOfficeHours(Meeting meeting) {
        final OfficeHours officeHours = this.officeHours.orElseThrow(() -> new IllegalStateException("Office hours are not set"));

        final LocalDateTime meetingStart = meeting.getStart();
        final LocalDateTime meetingEnd = meetingStart.plus(meeting.getDuration());

        final LocalDate meetingDate = meetingStart.toLocalDate();
        final LocalDateTime officeStart = meetingDate.atStartOfDay().plus(officeHours.getStart());
        final LocalDateTime officeEnd = meetingDate.atStartOfDay().plus(officeHours.getEnd());

        return !officeStart.isAfter(meetingStart) && !officeEnd.isBefore(meetingEnd);
    }
}
