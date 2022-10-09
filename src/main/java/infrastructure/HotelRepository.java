package infrastructure;

import domain.Hotel;

import java.util.Optional;

public interface HotelRepository {

    Optional<Hotel> findById(int hotelId);
}