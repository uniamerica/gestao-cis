package com.cis.model.dto.PatientDTO;

import com.cis.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientUpdateDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private UUID id;
  private UUID patientId;
  private String name;
  private String email;
  private String phone;
  private Character gender;
  private String addressNumber;
  private String addressLine2;

  // Address
  private String cep;
  private String street;
  private String city;
  private String uf;
  private String neighborhood;

  public PatientUpdateDTO(Patient patient) {
    this.id = patient.getId();
    this.patientId = patient.getPatientId();
    this.name = patient.getName();
    this.email = patient.getEmail();
    this.phone = patient.getPhone();
    this.gender = patient.getGender();
    this.addressNumber = patient.getAddressNumber();
    this.addressLine2 = patient.getAddressLine2();
    this.cep = patient.getAddress().getCep();
    this.street = patient.getAddress().getStreet();
    this.city = patient.getAddress().getCity();
    this.uf = patient.getAddress().getUf();
    this.neighborhood = patient.getAddress().getNeighborhood();
  }
}
