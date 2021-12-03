package com.gestaocis.backend.DTOs.ProfessionalDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfessionalRequestDTO {

    private String cpf;
    private String rg;
    private String professionalDocument;
    private String email;
    private String phone;
    private Instant birthdate;
    private Character sex;
    private String addressCountry;
    private String addressLine2;
    private String fullName;
    private String password;
    private List<String> specialties = new ArrayList<>();
}
