package com.group.booking.web;

import com.group.booking.web.data.in.BookingEntry;
import com.group.booking.web.data.in.BookingRequest;
import com.group.booking.web.data.out.BookingResponse;
import com.group.booking.web.data.out.DayMeetingList;
import com.group.booking.web.data.out.MeetingEntry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class BookingController {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    BookingResponse booking(@RequestBody BookingRequest bookingRequest)
    {
        final BookingEntry bookingEntry = bookingRequest.getBookings().get(0);

        final ArrayList<MeetingEntry> meetings = new ArrayList<>();
        final String meetingStart = bookingEntry.getMeetingStart();
        meetings.add(new MeetingEntry(meetingStart, meetingStart + "end", bookingEntry.getEmployeeId()));
        final DayMeetingList dayMeetingList = new DayMeetingList(meetingStart, meetings);
        final ArrayList<DayMeetingList> days = new ArrayList<>();
        days.add(dayMeetingList);
        return new BookingResponse(days);
    }
}
