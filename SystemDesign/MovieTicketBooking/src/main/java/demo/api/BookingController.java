package demo.api;

import demo.model.Seat;
import demo.model.Show;
import demo.services.BookingService;
import demo.services.ShowService;
import demo.services.TheatreService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
public class BookingController {

    private final ShowService showService;
    private final TheatreService theatreService;
    private final BookingService bookingService;

    public String createBooking(@NonNull final String userId, @NonNull final String showId, @NonNull final List<String> seatsIds) {

        final Show show = showService.getShow(showId);
        final List<Seat> seats = seatsIds.stream().map(theatreService::getSeat).collect(Collectors.toList());
        return bookingService.createBooking(userId, show, seats).getId();
    }
}
