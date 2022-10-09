package domain;

public class AlreadyExistingHotelException extends RuntimeException {

    public AlreadyExistingHotelException(int hotelId) {
        super("hotel with id " + hotelId + " was already registered");
    }
}
