package demo.cases;

import demo.api.*;
import demo.model.Show;
import demo.providers.InMemorySeatLockProvider;
import demo.providers.SeatLockProvider;
import demo.services.*;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    protected BookingController bookingController;
    protected MovieController movieController;
    protected PaymentsController paymentsController;
    protected ShowController showController;
    protected TheatreController theatreController;

    protected void setupControllers(int lockTimeout, int allowedRetries) {
        final SeatLockProvider seatLockProvider = new InMemorySeatLockProvider(lockTimeout);
        final BookingService bookingService = new BookingService(seatLockProvider);
        final MovieService movieService = new MovieService();
        final ShowService showService = new ShowService();
        final TheatreService theatreService = new TheatreService();
        final SeatAvailabilityService seatAvailabilityService
                = new SeatAvailabilityService(bookingService, seatLockProvider);
        final PaymentService paymentsService = new PaymentService(allowedRetries, seatLockProvider);

        bookingController = new BookingController(showService, theatreService, bookingService);
        showController = new ShowController(theatreService, showService, movieService, seatAvailabilityService);
        theatreController = new TheatreController(theatreService);
        movieController = new MovieController(movieService);
        paymentsController = new PaymentsController(paymentsService, bookingService);

    }

    protected void validateSeatsList(List<String> seatsList, List<String> allSeatsInScreen, List<String> excludedSeats) {
        for (String includedSeat: allSeatsInScreen) {
            if (!excludedSeats.contains(includedSeat)) {
                Assert.assertTrue(seatsList.contains(includedSeat));
            }
        }

        for (String excludedSeat: excludedSeats) {
            Assert.assertFalse(seatsList.contains(excludedSeat));
        }
    }

    protected List<String> createSeats(TheatreController theatreController, String screen, int numRows, int numSeatsInRow) {
        List<String> seats = new ArrayList<>();
        for (int row = 0; row < numRows; row++) {
            for (int seatNo = 0; seatNo < numSeatsInRow; seatNo++) {
                String seat = theatreController.createSeatInScreen(row, seatNo, screen);
                seats.add(seat);
            }
        }
        return seats;
    }

    protected String setupScreen() {
        final String theatre = theatreController.createTheatre("Theatre 1");
        return theatreController.createScreenInTheatre("Screen 1", theatre);
    }
}
