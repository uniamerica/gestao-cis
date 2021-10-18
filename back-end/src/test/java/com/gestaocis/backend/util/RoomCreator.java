package com.gestaocis.backend.util;

import com.gestaocis.backend.DTOs.RoomDTOs.NewRoomRequestDTO;
import com.gestaocis.backend.DTOs.RoomDTOs.RoomResponseDTO;
import com.gestaocis.backend.enums.SpecialtyEntity;
import com.gestaocis.backend.models.Appointment;
import com.gestaocis.backend.models.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomCreator {

  private static List<SpecialtyEntity> specialtyEntityList = new ArrayList<>();

  private static List<Appointment> appointments = new ArrayList<>();

  public static NewRoomRequestDTO createValidRoomRequestDTOSaved() {
    return NewRoomRequestDTO.builder()
        .roomNumber(02)
        .specialties("tortura")
        .appointments("10:00")
        .build();
  }

  public static RoomResponseDTO createValidRoomResponseDTOSaved() {
    return RoomResponseDTO.builder()
        .uuid(UUID.randomUUID())
        .roomNumber("02")
        .specialties("fisio")
        .appointments("11:00")
        .build();
  }

  public static Room createRoomToBeSaved() {
    return Room.builder()
        .uuid(UUID.randomUUID())
        .roomNumber(02)
        .specialties(specialtyEntityList)
        .appointments(appointments)
        .build();
  }
}
