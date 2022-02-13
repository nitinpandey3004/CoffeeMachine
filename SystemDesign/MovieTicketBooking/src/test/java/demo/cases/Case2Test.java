package demo.cases;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class Case2Test extends BaseTest{

    @BeforeEach
    void setup() {
        setupControllers(10, 0);
    }

    @Test
    void testcase2() {
        String user1 = "User1";
        String user2 = "User2";

        final String movie = movieController.createMovie("movie1");
        final String screen = setupScreen();
        final List<String> screen1SeatIds = createSeats(theatreController, screen, 2, 10);

        final String show = showController.createShow(movie, screen, new Date(), 2 * 60 * 60);

        List<String> u1AvailableSeats = showController.getAvailableSeats(show);
        // Validate that seats u1 received has all screen seats
        validateSeatsList(u1AvailableSeats, screen1SeatIds, ImmutableList.of());

        ImmutableList<String> u1SelectedSeats = ImmutableList.of(
                screen1SeatIds.get(0),
                screen1SeatIds.get(2),
                screen1SeatIds.get(5),
                screen1SeatIds.get(10)
        );

        final String bookingId = bookingController.createBooking(user1, show, u1SelectedSeats);

        // before failing
        final List<String> u2AvailableSeats = showController.getAvailableSeats(show);
        // Validate that u2 seats has all screen seats except the ones already booked by u1
        validateSeatsList(u2AvailableSeats, screen1SeatIds, u1SelectedSeats);

        paymentsController.paymentFailed(bookingId, user1);

        // after payment fails and 0 retry, u2 should see all available seats
        final List<String> u2AvailableSeatsAgain = showController.getAvailableSeats(show);
        validateSeatsList(u2AvailableSeatsAgain, screen1SeatIds, ImmutableList.of());
    }
}
