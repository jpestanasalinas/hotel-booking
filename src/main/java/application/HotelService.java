package application;

import domain.AlreadyExistingHotelException;
import domain.Hotel;
import domain.NonExistentHotelException;
import domain.Room;
import infrastructure.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    private final HotelRepository repository;

    @Autowired
    public HotelService(HotelRepository repository) {
        this.repository = repository;
    }

    void addHotel(int hotelId, String hotelName) {
        repository.findById(hotelId).ifPresent(s -> {
            throw new AlreadyExistingHotelException(hotelId);
        });

        Hotel hotel = new Hotel(hotelId, hotelName);

        repository.save(hotel);

    }

    void setRoom(int hotelId, int number, int roomType) {
        Hotel hotel = repository.findById(hotelId).orElseThrow(() -> new NonExistentHotelException(hotelId));

        Room room = new Room(number, roomType);
        hotel.setRoom(room);

        repository.saveOrUpdate(hotel);
    }

    Hotel findHotelBy(int hotelId) {
        return repository.findById(hotelId).orElseThrow(() -> new NonExistentHotelException(hotelId));
    }

}
