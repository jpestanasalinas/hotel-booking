package application;

import domain.AlreadyExistingHotelException;
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
        repository.findById(hotelId)
                .ifPresent(s -> {
                    throw new AlreadyExistingHotelException(hotelId);
                });


    }

    //void setRoom(<?> hotelId, <?> number, <?> roomType);

    //<?> findHotelBy(<?> hotelId);

}
