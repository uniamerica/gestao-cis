package com.gestaocis.backend.DTOs.SpecialtyDTOs;

import com.gestaocis.backend.enums.SpecialtyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyListDTO {

    private UUID uuid;
    private String specialty;

    public SpecialtyListDTO(SpecialtyEntity specialtyEntity){
        this.uuid = specialtyEntity.getUuid();
        this.specialty = specialtyEntity.getSpecialtyName();
    }

}
