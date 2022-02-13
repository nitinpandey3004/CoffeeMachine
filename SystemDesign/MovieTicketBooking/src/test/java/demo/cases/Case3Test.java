package demo.cases;

import com.google.common.collect.ImmutableList;
import demo.exceptions.SeatPermanentlyUnavailableException;
import demo.exceptions.SeatTemporaryUnavailableException;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;
import java.util.List;

public class Case3Test extends BaseTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @BeforeEach
    void setup() {
        setupControllers(10, 0);
    }

    @Test
    void testcase3() {
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

        final String bookingIdU1 = bookingController.createBooking(user1, show, u1SelectedSeats);

        ImmutableList<String> u2SelectedSeats = ImmutableList.of(
                screen1SeatIds.get(0),
                screen1SeatIds.get(2)
        );

        Assertions.assertThrows(SeatTemporaryUnavailableException.class, () -> {
            final String bookingIdU2 = bookingController.createBooking(user2, show, u2SelectedSeats);
        });

        paymentsController.paymentSuccess(bookingIdU1, user1);

        Assertions.assertThrows(SeatPermanentlyUnavailableException.class, () -> {
            final String bookingIdU2 = bookingController.createBooking(user2, show, u2SelectedSeats);
        });

    }
}
