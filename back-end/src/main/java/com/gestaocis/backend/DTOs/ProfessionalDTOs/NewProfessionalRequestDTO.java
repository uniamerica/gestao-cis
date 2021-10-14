package com.gestaocis.backend.DTOs.ProfessionalDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewProfessionalRequestDTO {
    // User
    private String cpf;
    private String rg;
    private String email;
    private String phone;
    private String fullName;
    private String mothersName;
    private String birthdate;
    private Character sex;
    private String placeOfBirth;
    private String addressCountry;
    private String addressLine2;
    private String password;
    private String professionalDocument;

    // Address
    private String cep;
    private String street;
    private String city;
    private String uf;
    private String neighborhood;
}
