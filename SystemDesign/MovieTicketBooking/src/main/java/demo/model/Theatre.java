package demo.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Theatre {

    private final String id;
    private final String name;
    private final List<Screen> screenList;

    public Theatre(@NonNull final String id, @NonNull final String name) {
        this.id = id;
        this.name = name;
        this.screenList = new ArrayList<>();
    }

    public void addScreen(@NonNull final Screen screen) {
        this.screenList.add(screen);
    }

}
