package demo.services;

import demo.exceptions.NotFoundException;
import demo.model.Movie;
import demo.model.Screen;
import demo.model.Show;
import lombok.NonNull;

import java.util.*;

public class ShowService {

    private final Map<String, Show> shows;

    public ShowService() {
        this.shows = new HashMap<>();
    }

    public Show getShow(@NonNull final String id) {
        if (!shows.containsKey(id)) {
            throw new NotFoundException();
        }
        return shows.get(id);
    }

    public Show createShow(@NonNull final Movie movie, @NonNull final Screen screen, @NonNull final Date startTime,
                           @NonNull final Integer durationInSeconds) {

        String showId = UUID.randomUUID().toString();
        final Show show = new Show(showId, movie, screen, startTime, durationInSeconds);
        this.shows.put(showId, show);
        return show;
    }

    public List<Show> getShowsForScreen(Screen screen) {
        final List<Show> response = new ArrayList<>();
        for (Show show : shows.values()) {
            if (show.getScreen().equals(screen)) {
                response.add(show);
            }
        }
        return response;
    }
}
