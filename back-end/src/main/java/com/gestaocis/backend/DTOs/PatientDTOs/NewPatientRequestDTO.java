package com.gestaocis.backend.DTOs.PatientDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPatientRequestDTO {
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
    private String insuranceName;
    private String registrationNumber;

    // Address
    private String cep;
}
