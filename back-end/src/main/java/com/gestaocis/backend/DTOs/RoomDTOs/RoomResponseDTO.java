package com.gestaocis.backend.DTOs.RoomDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponseDTO {
    private UUID uuid;
    private String roomNumber;
    private String specialties;
    private String appointments;

}
