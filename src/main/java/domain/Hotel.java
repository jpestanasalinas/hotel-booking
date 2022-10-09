package domain;

public class Hotel {

    private final int id;
    private final String name;

    private final Rooms rooms;

    public Hotel(int id, String name) {
        this.id = id;
        this.name = name;
        this.rooms = new Rooms();
    }

    public Rooms rooms() {
        return rooms;
    }

    public void setRoom(Room room) {
        if (!rooms.exist(room.number()))
            rooms.add(room);

        rooms.update(room);
    }
}
