package com.gestaocis.backend.DTOs.SpecialtyDTOs;

import com.gestaocis.backend.DTOs.ProfessionalDTO.ProfessionalListDTO;
import com.gestaocis.backend.enums.SpecialtyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyDTO {

    private UUID uuid;
    private String specialty;
    private List<ProfessionalListDTO> professionals = new ArrayList<>();

    public SpecialtyDTO(SpecialtyEntity specialty){
        this.uuid = specialty.getUuid();
        this.specialty = specialty.getSpecialtyName();
        this.professionals = specialty.getUsers().stream().map(ProfessionalListDTO::new).collect(Collectors.toList());
    }

}
