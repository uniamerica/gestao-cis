package com.gestaocis.backend.dtos.specialty;

import com.gestaocis.backend.dtos.user.UserListDTO;
import com.gestaocis.backend.models.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyDTO {

    private UUID uuid;
    private String title;
    private String description;
    private Set<UserListDTO> specialists;

    public SpecialtyDTO(Specialty specialty){
        uuid = specialty.getUuid();
        title = specialty.getTitle();
        description = specialty.getDescription();
        specialists = specialty.getEspecialists().stream().map(UserListDTO::new).collect(Collectors.toSet());
    }

}
