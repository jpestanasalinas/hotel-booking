package domain;

public class Room {
    private final int number;
    private int type;

    public Room(int number, int type) {
        this.number = number;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (number != room.number) return false;
        return type == room.type;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + type;
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "number=" + number +
                ", type=" + type +
                '}';
    }

    public int number() {
        return number;
    }

    public int type() {
        return type;
    }

    public void changeType(int type) {
        this.type = type;
    }
}
