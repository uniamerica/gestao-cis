package com.gestaocis.backend.DTOs.PatientDTOs;

import com.gestaocis.backend.DTOs.AddressDTO.AddressDTO;
import com.gestaocis.backend.DTOs.HealthInsuranceDTO.HealthInsuranceDTO;
import com.gestaocis.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDTO {
    // User
    private UUID uuid;
    private String cpf;
    private String rg;
    private String email;
    private String phone;
    private String fullName;
    private String mothersName;
    private Instant birthdate;
    private Character sex;
    private String placeOfBirth;
    private String password;
    // Convênio
    private HealthInsuranceDTO healthInsurance;

    private AddressDTO address;

    public PatientResponseDTO(User patient) {
        this.uuid = patient.getUuid();
        this.cpf = patient.getCpf();
        this.rg = patient.getRg();
        this.email = patient.getEmail();
        this.phone = patient.getPhone();
        this.fullName = patient.getFullName();
        this.mothersName = patient.getMothersName();
        this.birthdate = patient.getBirthdate();
        this.sex = patient.getSex();
        this.placeOfBirth = patient.getPlaceOfBirth();
        this.address = AddressDTO.builder()
                .addressCountry(patient.getAddressCountry())
                .addressLine2(patient.getAddressLine2())
                .cep(patient.getAddress().getCep())
                .city(patient.getAddress().getCity())
                .neighborhood(patient.getAddress().getNeighborhood())
                .uf(patient.getAddress().getUf())
                .street(patient.getAddress().getStreet())
                .build();
        this.healthInsurance = HealthInsuranceDTO.builder()
                .insuranceName(patient.getHealthInsurance().getInsuranceName())
                .registrationNumber(patient.getHealthInsurance().getRegistrationNumber())
                .build();
        }

}
