package com.group.booking.web;

import com.group.booking.MeetingsStorage;
import com.group.booking.OfficeHoursStorage;
import com.group.booking.data.Meeting;
import com.group.booking.data.MeetingRequest;
import com.group.booking.data.OfficeHours;
import com.group.booking.web.data.in.BookingRequest;
import com.group.booking.web.data.out.BookingResponse;
import com.group.booking.web.data.out.DayMeetingList;
import com.group.booking.web.data.out.MeetingEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class BookingController {

    @Autowired
    MeetingsStorage meetingsStorage;

    @Autowired
    OfficeHoursStorage officeHoursStorage;

    @Autowired
    ConversionService conversionService;

    @RequestMapping(value = "/bookings", method = RequestMethod.POST)
    @ResponseBody
    synchronized BookingResponse booking(@RequestBody BookingRequest bookingRequest) {
        registerOfficeHours(bookingRequest);
        registerMeeting(bookingRequest);

        final List<Meeting> allMeetings = meetingsStorage.getAllMeetings();
        final BookingResponse response = groupByDayAndConvert(allMeetings);
        return response;
    }

    private void registerOfficeHours(@RequestBody BookingRequest bookingRequest) {
        final OfficeHours officeHours = conversionService.convert(bookingRequest.getOfficeHours(), OfficeHours.class);

        // todo: need a better idea to handle multiple office hours
        if (!officeHoursStorage.isOfficeHoursSet()) {
            officeHoursStorage.setOfficeHours(officeHours);
        }
    }

    private void registerMeeting(@RequestBody BookingRequest bookingRequest) {
        final List<MeetingRequest> meetingRequests = bookingRequest.getBookings().stream()
                .map(bookingEntry -> conversionService.convert(bookingEntry, MeetingRequest.class))
                .sorted(Comparator.comparing(MeetingRequest::getRequestTime))
                .collect(Collectors.toList());

        meetingRequests.stream()
                .filter(meetingRequest -> officeHoursStorage.isMeetingWithinOfficeHours(meetingRequest.getMeeting()))
                .forEach(meetingRequest -> {
                    final Meeting meeting = meetingRequest.getMeeting();
                    // todo: need a simplier api
                    if (meetingsStorage.isMeetingAllowed(meeting)) {
                        meetingsStorage.addMeeting(meeting);
                    }
                });
    }

    @RequestMapping(value = "/bookings", method = RequestMethod.GET)
    @ResponseBody
    synchronized BookingResponse getBookingsByDate(@RequestParam String date) {
        final LocalDate targetDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        final List<Meeting> meetings = meetingsStorage.getMeetingsByDate(targetDate);
        final BookingResponse response = groupByDayAndConvert(meetings);
        return response;
    }

    private BookingResponse groupByDayAndConvert(List<Meeting> meetings) {
        final Map<LocalDate, List<Meeting>> meetingsGroupByDate = meetings.stream().sorted(Comparator.comparing(Meeting::getStart)).collect(Collectors.groupingBy(meeting -> meeting.getStart().toLocalDate()));
        final ArrayList<DayMeetingList> days = new ArrayList<>();
        meetingsGroupByDate.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).forEach(entry -> {
            final String date = entry.getKey().format(DateTimeFormatter.ISO_LOCAL_DATE);
            final List<MeetingEntry> meetingEntries = entry.getValue().stream().map(meeting -> conversionService.convert(meeting, MeetingEntry.class)).collect(Collectors.toList());
            final DayMeetingList dayMeetingList = new DayMeetingList(date, meetingEntries);
            days.add(dayMeetingList);
        });
        return new BookingResponse(days);
    }
}
