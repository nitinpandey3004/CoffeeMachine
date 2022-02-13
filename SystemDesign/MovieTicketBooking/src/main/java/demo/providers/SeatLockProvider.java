package demo.providers;

import demo.exceptions.SeatTemporaryUnavailableException;
import demo.model.Seat;
import demo.model.Show;

import java.util.List;

public interface SeatLockProvider {
    void lockSeats(Show show, List<Seat> seatList, String user) throws SeatTemporaryUnavailableException;
    void unlockSeats(Show show, List<Seat> seat, String user);
    boolean validateLock(Show show, Seat seat, String user);

    List<Seat> getLockedSeats(Show show);
}
