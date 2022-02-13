package demo.api;

import demo.services.BookingService;
import demo.services.PaymentService;
import lombok.NonNull;

public class PaymentsController {

    private final PaymentService paymentsService;
    private final BookingService bookingService;

    public PaymentsController(PaymentService paymentsService, BookingService bookingService) {
        this.paymentsService = paymentsService;
        this.bookingService = bookingService;
    }

    public void paymentFailed(@NonNull final String bookingId, @NonNull final String user) {
        paymentsService.processPaymentFailed(bookingService.getBooking(bookingId), user);
    }

    public void paymentSuccess(@NonNull final  String bookingId, @NonNull final String user) {
        bookingService.confirmBooking(bookingService.getBooking(bookingId), user);
    }
}
