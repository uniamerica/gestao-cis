package com.gestaocis.backend.DTOs.RoomDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewRoomRequestDTO {
    private Integer roomNumber;
    private String specialties;
    private String appointments;

}
