package com.gestaocis.backend.DTOs.PatientDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPatientRequestDTO implements Serializable {

  private static final long serialVersionUID = 1L;

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
  //    private String insuranceName;
  private String registrationNumber;

  // Address
  private String cep;
  private String street;
  private String city;
  private String uf;
  private String neighborhood;
}
