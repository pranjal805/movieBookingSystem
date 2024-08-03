package com.mangoapps.booking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private Long bookingId;
    private String movieTitle;
    private LocalDateTime showTime;
    private int seatNumber;
    private int bookedSeats;
    private int totalAvailableSeats;
    private List<Integer> availableSeatNumbers = new ArrayList<>();
}
