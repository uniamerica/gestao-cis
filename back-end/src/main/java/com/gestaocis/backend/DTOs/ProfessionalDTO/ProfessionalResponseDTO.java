package com.gestaocis.backend.DTOs.ProfessionalDTO;

import com.gestaocis.backend.DTOs.AddressDTO.AddressDTO;
import com.gestaocis.backend.models.User;

import java.time.Instant;
import java.util.UUID;

public class ProfessionalResponseDTO {
    // User
    private UUID uuid;
    private String email;
    private String phone;
    private String fullName;
    private String mothersName;
    private Instant birthdate;
    private Character sex;
    private String password;

    public ProfessionalResponseDTO(User patient) {
        this.uuid = patient.getUuid();
        this.fullName = patient.getFullName();
        this.email = patient.getEmail();
        this.phone = patient.getPhone();
        this.mothersName = patient.getMothersName();
        this.birthdate = patient.getBirthdate();
        this.sex = patient.getSex();
    }

}
