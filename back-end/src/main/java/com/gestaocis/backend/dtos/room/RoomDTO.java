package com.gestaocis.backend.dtos.room;

import com.gestaocis.backend.dtos.specialty.SpecialtyForListDTO;
import com.gestaocis.backend.models.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    private UUID uuid;
    private Integer roomNumber;
    private Set<SpecialtyForListDTO> specialties;

    public RoomDTO(Room room){
        uuid = room.getUuid();
        roomNumber = room.getRoomNumber();
        specialties = room.getSpecialties().stream().map(SpecialtyForListDTO::new).collect(Collectors.toSet());
    }
}
