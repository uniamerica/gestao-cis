package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Room;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    private Room createRoom(){
        return Room
                .builder()
                .roomNumber(113)
                .build();
    }

    @Test
    @DisplayName("Save Room When Successful")
    void save_room_whenSuccessful(){
        Room room = createRoom();

        Room roomSaved = roomRepository.save(room);

        Assertions
                .assertThat(roomSaved).isNotNull();
    }

    @Test
    @DisplayName("Find Room By Id When Successful")
    void find_roomById_whenSuccessful(){
        Room room = createRoom();

        Room roomSaved = roomRepository.save(room);

        Long id = roomSaved.getId();

        Optional<Room> roomFound = roomRepository.findById(id);

        Assertions.assertThat(roomFound).isNotNull();
        Assertions.assertThat(roomFound.get().getRoomNumber()).isEqualTo(roomSaved.getRoomNumber());


    }

    @Test
    @DisplayName("Find Room By Room Number When Successful")
    void find_roomByRoomNumber_whenSuccessful(){
        Room room = createRoom();

        Room roomSaved = roomRepository.save(room);

        Integer number = roomSaved.getRoomNumber();

        Optional<Room> roomFound = roomRepository.findByRoomNumber(number);

        Assertions.assertThat(roomFound).isNotNull();
        Assertions.assertThat(roomFound.get()).isEqualTo(roomSaved);
    }

    @Test
    @DisplayName("Update Room When Successful")
    void update_room_whenSuccessful(){
        Room room = createRoom();
        Room roomToCompare = createRoom();

        Room roomSaved = roomRepository.save(room);

        roomSaved.setRoomNumber(99);

        Room roomUpdated = roomRepository.save(roomSaved);

        Assertions.assertThat(roomUpdated.getRoomNumber()).isNotEqualTo(roomToCompare.getRoomNumber());

    }

    @Test
    @DisplayName("Find List of Room When Successful")
    void find_Rooms_whenSuccessful(){
        Room room = createRoom();

        Room roomSaved = roomRepository.save(room);

        List<Room> roomList = roomRepository.findAll();

        Assertions
                .assertThat(roomList)
                .isNotNull()
                .isNotEmpty()
                .contains(roomSaved);


    }

    @Test
    @DisplayName("Delete Room When Successful")
    void delete_room_whenSuccessful(){
        Room room = createRoom();

        Room roomSaved = roomRepository.save(room);

        roomRepository.delete(roomSaved);

        List<Room> roomList = roomRepository.findAll();

        Assertions
                .assertThat(roomList)
                .isNotNull()
                .doesNotContain(roomSaved);
    }


}