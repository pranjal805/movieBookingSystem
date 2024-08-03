package com.mangoapps.booking.services;

import com.mangoapps.booking.models.Booking;
import com.mangoapps.booking.models.BookingResponse;
import com.mangoapps.booking.models.Show;
import com.mangoapps.booking.repositories.BookingRepository;
import com.mangoapps.booking.repositories.MovieRepository;
import com.mangoapps.booking.repositories.ShowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class BookingService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public BookingResponse bookTicket(Long showId, String userName) {
        log.info("We have entered bookTicket");
        Optional<Show> showOptional = showRepository.findById(showId);
        if (showOptional.isPresent()) {
            Show show = showOptional.get();
            if (show.getAvailableSeats() > 0 && !show.getAvailableSeatNumbers().isEmpty()) {
                Booking booking = new Booking();
                booking.setUserName(userName);
                booking.setSeatNumber(show.getAvailableSeatNumbers().remove(0));
                booking.setShow(show);
                show.setAvailableSeats(show.getAvailableSeats() - 1);
                showRepository.save(show);
                bookingRepository.save(booking);
                return  new BookingResponse(booking.getId(),show.getMovie().getTitle(),show.getShowTime(),booking.getSeatNumber(),show.getTotalSeats()-show.getAvailableSeats(),show.getAvailableSeats(),show.getAvailableSeatNumbers());
            }
        }
        return null;
    }

    public boolean cancelBooking(Long bookingId) {
        log.info("We have entered cancelBooking");
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            Show show = booking.getShow();
            show.setAvailableSeats(show.getAvailableSeats() + 1);
            show.getAvailableSeatNumbers().add(booking.getSeatNumber());
            show.getAvailableSeatNumbers().sort(Integer::compareTo);
            showRepository.save(show);
            bookingRepository.delete(booking);
            return true;
        }
        return false;
    }

}