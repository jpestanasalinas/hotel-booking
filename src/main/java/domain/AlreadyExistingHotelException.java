package domain;

public class AlreadyExistingHotelException extends RuntimeException {

    public AlreadyExistingHotelException() {
        super("hotel with id 1 was already registered");
    }
}
