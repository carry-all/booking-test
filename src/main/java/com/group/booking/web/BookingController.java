package com.group.booking.web;

import com.group.booking.MeetingsStorage;
import com.group.booking.OfficeHoursStorage;
import com.group.booking.data.Meeting;
import com.group.booking.data.MeetingRequest;
import com.group.booking.data.OfficeHours;
import com.group.booking.web.data.in.BookingEntry;
import com.group.booking.web.data.in.BookingRequest;
import com.group.booking.web.data.out.BookingResponse;
import com.group.booking.web.data.out.DayMeetingList;
import com.group.booking.web.data.out.MeetingEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookingController {

    @Autowired
    MeetingsStorage meetingsStorage;

    @Autowired
    OfficeHoursStorage officeHoursStorage;

    @Autowired
    ConversionService conversionService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    BookingResponse booking(@RequestBody BookingRequest bookingRequest) {
        final OfficeHours officeHours = conversionService.convert(bookingRequest.getOfficeHours(), OfficeHours.class);

        // todo: need a better idea to handle multiple office hours
        if (!officeHoursStorage.isOfficeHoursSet()) {
            officeHoursStorage.setOfficeHours(officeHours);
        }

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

        final List<MeetingEntry> meetingEntries = meetingsStorage.getAllMeetings().stream().map(meeting -> conversionService.convert(meeting, MeetingEntry.class)).collect(Collectors.toList());
        final DayMeetingList dayMeetingList = new DayMeetingList("Fiction", meetingEntries);
        final ArrayList<DayMeetingList> days = new ArrayList<>();
        days.add(dayMeetingList);
        return new BookingResponse(days);
    }
}
