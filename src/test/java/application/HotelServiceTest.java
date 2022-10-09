package application;

import domain.AlreadyExistingHotelException;
import domain.Hotel;
import domain.NonExistentHotelException;
import domain.Room;
import infrastructure.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    public static final int HOTEL_ID = 1;
    public static final String HOTEL_NAME = "hotel1";
    @InjectMocks
    private HotelService service;
    @Mock
    private HotelRepository repository;

    @Test
    public void when_add_hotel_which_id_already_exists_should_throw_exception() {
        when(repository.findById(HOTEL_ID)).thenReturn(Optional.of(new Hotel(HOTEL_ID, HOTEL_NAME)));

        AlreadyExistingHotelException exception = assertThrows(AlreadyExistingHotelException.class, () -> {
            service.addHotel(HOTEL_ID, HOTEL_NAME);
            service.addHotel(HOTEL_ID, HOTEL_NAME);
        });

        assertEquals("hotel with id 1 was already registered", exception.getMessage());
    }

    @Test
    public void when_add_a_valid_hotel_should_save_it() {
        when(repository.findById(HOTEL_ID)).thenReturn(Optional.empty());

        service.addHotel(HOTEL_ID, HOTEL_NAME);

        verify(repository, times(1)).save(any());
    }

    @Test
    public void when_set_room_of_nonexistent_hotel_then_throws_exception() {
        when(repository.findById(HOTEL_ID)).thenReturn(Optional.empty());

        NonExistentHotelException exception = assertThrows(NonExistentHotelException.class,
                () -> service.setRoom(HOTEL_ID, 2, 1));

        assertEquals("hotel with id 1 does not exist", exception.getMessage());
    }

    @Test
    public void when_set_new_room_in_valid_hotel_then_insert_new_room() {
        Hotel hotel = new Hotel(HOTEL_ID, HOTEL_NAME);
        Room room = new Room(2, 1);

        when(repository.findById(HOTEL_ID)).thenReturn(Optional.of(hotel));

        service.setRoom(HOTEL_ID, 2, 1);

        assertTrue(hotel.rooms().contains(room));
        verify(repository, times(1)).saveOrUpdate(any());
    }

    @Test
    public void when_set_existent_room_in_valid_hotel_then_update_room() {
        Hotel hotel = new Hotel(HOTEL_ID, HOTEL_NAME);
        Room room = new Room(2, 2);

        when(repository.findById(HOTEL_ID)).thenReturn(Optional.of(hotel));

        service.setRoom(HOTEL_ID, 2, 1);
        service.setRoom(HOTEL_ID, 2, 2);

        assertTrue(hotel.rooms().contains(room));
        assertEquals(room, hotel.rooms().get(0));
        verify(repository, times(2)).saveOrUpdate(any());
    }
}