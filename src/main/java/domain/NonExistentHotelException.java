package domain;

public class NonExistentHotelException extends RuntimeException {
    public NonExistentHotelException(int hotelId) {
        super("hotel with id " + hotelId + " does not exist");
    }
}
