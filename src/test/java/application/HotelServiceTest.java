package application;

import domain.AlreadyExistingHotelException;
import domain.Hotel;
import infrastructure.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @InjectMocks
    private HotelService hotelService;
    @Mock
    private HotelRepository repository;

    @Test
    public void when_add_hotel_which_id_already_exists_should_throw_exception() {
        when(repository.findById(1)).thenReturn(Optional.of(new Hotel()));

        AlreadyExistingHotelException exception = assertThrows(AlreadyExistingHotelException.class, () -> {
            hotelService.addHotel(1, "hotel1");
            hotelService.addHotel(1, "hotel1");
        });

        assertEquals("hotel with id 1 was already registered", exception.getMessage());
    }

}