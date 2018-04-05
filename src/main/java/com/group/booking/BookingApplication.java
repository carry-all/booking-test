package com.group.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
    }

    @Bean
    public InMemoryOfficeHoursStorage inMemoryOfficeHoursStorage() {
        return new InMemoryOfficeHoursStorage();
    }

    @Bean
    public InMemoryMeetingsStorage inMemoryMeetingsStorage() {
        return new InMemoryMeetingsStorage();
    }
}
