package domain;

import java.util.ArrayList;

public class Rooms extends ArrayList<Room> {
    public boolean exist(int number) {
        return stream().anyMatch(it -> it.number() == number);
    }

    public void update(Room room) {
        stream().filter(it -> it.number() == room.number())
                .findAny()
                .ifPresent(it -> it.changeType(room.type()));
    }
}
