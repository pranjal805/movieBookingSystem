package com.mangoapps.booking.controllers;

import com.mangoapps.booking.models.BookingResponse;
import com.mangoapps.booking.services.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<BookingResponse> bookTicket(@RequestParam Long showId, @RequestParam String userName) {
        log.info("We have entered bookTicket controller");
        try {
            BookingResponse response = bookingService.bookTicket(showId, userName);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error occurred while booking ticket: " + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<Boolean> cancelBooking(@RequestParam Long bookingId) {
        log.info("We have entered cancelBooking controller");
        try {
            boolean result = bookingService.cancelBooking(bookingId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error occurred while canceling booking: " + e.getMessage());
            return ResponseEntity.badRequest().body(false);
        }
    }

}
