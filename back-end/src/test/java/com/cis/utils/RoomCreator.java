package com.cis.utils;

import com.cis.model.Room;
import com.cis.model.dto.RoomDTO.NewRoomRequestDTO;
import com.cis.model.dto.RoomDTO.RoomResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RoomCreator {

  // private static List<SpecialtyEntity> specialtyEntityList = new ArrayList<>();

  // private static List<Appointment> appointments = new ArrayList<>();

  public static NewRoomRequestDTO createValidRoomRequestDTOSaved() {
    return NewRoomRequestDTO.builder()
        .roomNumber(02)
        //   .specialties("tortura")
        .appointments("10:00")
        .build();
  }

  public static RoomResponseDTO createValidRoomResponseDTOSaved() {
    return RoomResponseDTO.builder()
        .id(UUID.randomUUID())
        .roomNumber("02")
        // .specialties("fisio")
        .build();
  }

  public static Room createRoomToBeSaved() {
    return Room.builder()
        .id(UUID.randomUUID())
        .roomNumber("02")
        // .specialties(specialtyEntityList)
        // .appointments(appointments)
        .build();
  }

  public static Room createRoomSaved() {
    return Room.builder().id(UUID.randomUUID()).roomNumber("01").build();
  }

  public static Optional<Room> createOptionalRoomToBeSaved() {
    Room room =
        Room.builder()
            .id(UUID.randomUUID())
            .roomNumber("02")
            //                .specialties(specialtyEntityList)
            //                .appointments(appointments)
            .build();
    return Optional.of(room);
  }

  public static List<Room> createRoomList() {
    Room room1 =
        Room.builder()
            .id(UUID.randomUUID())
            .roomNumber("01")
            //                        .specialties(specialtyEntityList)
            //                        .appointments(appointments)
            .build();

    Room room2 =
        Room.builder()
            .id(UUID.randomUUID())
            .roomNumber("02")
            //                        .specialties(specialtyEntityList)
            //                        .appointments(appointments)
            .build();

    Room room3 =
        Room.builder()
            .id(UUID.randomUUID())
            .roomNumber("03")
            //                        .specialties(specialtyEntityList)
            //                        .appointments(appointments)
            .build();

    List<Room> roomList = new ArrayList<>();

    roomList.add(room1);
    roomList.add(room2);
    roomList.add(room3);

    return roomList;
  }
}
