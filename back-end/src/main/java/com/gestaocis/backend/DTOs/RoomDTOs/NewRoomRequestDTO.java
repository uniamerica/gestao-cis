package com.gestaocis.backend.DTOs.RoomDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewRoomRequestDTO {
    private String roomNumber;
    private String specialties;
    private String appointments;
}
