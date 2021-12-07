package com.cis.model.dto.RoomDTO;

import com.cis.model.Room;
import com.cis.model.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponseDTO {
  private UUID id;
  private String roomNumber;
  private List<Specialty> specialties;

  public RoomResponseDTO(Room room) {
    this.id = room.getId();
    this.roomNumber = room.getRoomNumber();
    this.specialties = room.getSpecialties();
  }
}
