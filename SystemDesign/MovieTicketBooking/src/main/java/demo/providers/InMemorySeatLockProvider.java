package demo.providers;

import com.google.common.collect.ImmutableList;
import demo.exceptions.SeatTemporaryUnavailableException;
import demo.model.Seat;
import demo.model.SeatLock;
import demo.model.Show;
import lombok.NonNull;

import java.util.*;

public class InMemorySeatLockProvider implements SeatLockProvider{

    private final Integer lockTimeout;
    private final Map<Show, Map<Seat, SeatLock>> locks;

    public InMemorySeatLockProvider(@NonNull final Integer lockTimeout) {
        this.locks = new HashMap<>();
        this.lockTimeout = lockTimeout;
    }

    private boolean isSeatLocked(final Show show, final Seat seat) {
        return locks.containsKey(show) && locks.get(show).containsKey(seat) && !locks.get(show).get(seat).isLockExpired();
    }

    private void lockSeat(final Show show, final Seat seat, final String user, final Integer timeoutInSeconds) {
        if (!locks.containsKey(show)) {
            locks.put(show, new HashMap<>());
        }
        final SeatLock lock = new SeatLock(seat, show, timeoutInSeconds, new Date(), user);
        locks.get(show).put(seat, lock);
    }

    @Override
    synchronized public void lockSeats(@NonNull final Show show, @NonNull final List<Seat> seats,
                                       @NonNull final String user) {

        for (Seat seat: seats) {
            if (isSeatLocked(show, seat)) {
                throw new SeatTemporaryUnavailableException();
            }
        }

        for (Seat seat: seats) {
            lockSeat(show, seat, user, lockTimeout);
        }
    }

    private void unlockSeat(final Show show, final Seat seat) {
        if (!locks.containsKey(show)) {
            return;
        }
        locks.get(show).remove(seat);
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seats, String user) {
        for (Seat seat: seats) {
            if (validateLock(show, seat, user)) {
                unlockSeat(show, seat);
            }
        }
    }

    @Override
    public boolean validateLock(Show show, Seat seat, String user) {
        return isSeatLocked(show, seat) && locks.get(show).get(seat).getLockedBy().equals(user);
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        if (!locks.containsKey(show)) {
            return ImmutableList.of();
        }
        final List<Seat> lockedSeats = new ArrayList<>();

        for (Seat seat : locks.get(show).keySet()) {
            if (isSeatLocked(show, seat)) {
                lockedSeats.add(seat);
            }
        }

        return lockedSeats;
    }
}
