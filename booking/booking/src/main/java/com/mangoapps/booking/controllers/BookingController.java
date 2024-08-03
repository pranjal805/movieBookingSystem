package com.mangoapps.booking.controllers;

import com.mangoapps.booking.models.BookingResponse;
import com.mangoapps.booking.services.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public BookingResponse bookTicket(@RequestParam Long showId, @RequestParam String userName) {
        log.info("We have entered bookTicket controller");
        return bookingService.bookTicket(showId, userName);
    }

    @DeleteMapping("/cancel")
    public boolean cancelBooking(@RequestParam Long bookingId) {
        log.info("We have entered cancelBooking controller");
        return bookingService.cancelBooking(bookingId);
    }

}
