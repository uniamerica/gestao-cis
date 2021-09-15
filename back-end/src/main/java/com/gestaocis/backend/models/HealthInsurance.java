package com.gestaocis.backend.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HealthInsurance implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;

  private String insuranceName;
  private String registrationNumber;

  @OneToMany private List<User> users = new ArrayList<>();

  public HealthInsurance() {}

  public HealthInsurance(Long id, UUID uuid, String insuranceName, String registrationNumber) {
    this.id = id;
    this.uuid = uuid;
    this.insuranceName = insuranceName;
    this.registrationNumber = registrationNumber;
  }

  public Long getId() {
    return id;
  }

  public UUID getUuid() {
    return uuid;
  }

  public String getInsuranceName() {
    return insuranceName;
  }

  public void setInsuranceName(String insuranceName) {
    this.insuranceName = insuranceName;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public List<User> getUsers() {
    return users;
  }
}
