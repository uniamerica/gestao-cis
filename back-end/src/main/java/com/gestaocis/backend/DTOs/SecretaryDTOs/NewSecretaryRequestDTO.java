package com.gestaocis.backend.DTOs.SecretaryDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewSecretaryRequestDTO {

    // User
    private String cpf;
    private String rg;
    private String email;
    private String phone;
    private String fullName;
    private String birthdate;
    private Character sex;
    private String addressCountry;
    private String addressLine2;
    private String password;

    // Address
    private String cep;

}