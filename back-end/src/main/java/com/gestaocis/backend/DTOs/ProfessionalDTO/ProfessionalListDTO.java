package com.gestaocis.backend.DTOs.ProfessionalDTO;

import com.gestaocis.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalListDTO {

    private UUID uuid;
    private String email;
    private String phone;
    private String fullName;

    public ProfessionalListDTO(User patient) {
        this.uuid = patient.getUuid();
        this.fullName = patient.getFullName();
        this.email = patient.getEmail();
        this.phone = patient.getPhone();
    }
}
