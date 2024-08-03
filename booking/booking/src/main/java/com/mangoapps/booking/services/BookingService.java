package com.mangoapps.booking.services;

import com.mangoapps.booking.models.BookingResponse;

public interface BookingService {
    public BookingResponse bookTicket(Long showId, String userName);
    public boolean cancelBooking(Long bookingId);
}
