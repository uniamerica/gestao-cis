package com.gestaocis.backend.DTOs.ProfessionalDTO;

import com.gestaocis.backend.DTOs.AddressDTO.AddressDTO;
import com.gestaocis.backend.DTOs.SpecialtyDTOs.SpecialtyListDTO;
import com.gestaocis.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalResponseDTO {
    // User
    private UUID uuid;
    private String email;
    private String phone;
    private String fullName;
    private List<SpecialtyListDTO> specialties = new ArrayList<>();

    public ProfessionalResponseDTO(User professional) {
        this.uuid = professional.getUuid();
        this.fullName = professional.getFullName();
        this.email = professional.getEmail();
        this.phone = professional.getPhone();
        this.specialties = professional.getSpecialties().stream().map(SpecialtyListDTO::new).collect(Collectors.toList());
    }

}
