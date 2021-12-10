package com.cis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthProfessional extends User implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(updatable = false, nullable = false)
  private UUID professional_id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false)
  private String professionalDocument;

  @OneToOne
  private Specialty specialty;

  public Specialty getSpecialty() {
    return specialty;
  }

  public void setSpecialty(Specialty specialty) {
    this.specialty = specialty;
  }

  @OneToMany(mappedBy = "professional")
  private List<Schedule> schedules;

  public UUID getProfessional_id() {
    return professional_id;
  }

  public void setProfessional_id(UUID professional_id) {
    this.professional_id = professional_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getProfessionalDocument() {
    return professionalDocument;
  }

  public void setProfessionalDocument(String professionalDocument) {
    this.professionalDocument = professionalDocument;
  }

  public List<Schedule> getSchedules() {
    return schedules;
  }

  public void setSchedules(List<Schedule> schedules) {
    this.schedules = schedules;
  }
}
