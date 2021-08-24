package com.gestaocis.backend.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {

  UUID id;
  User patient;
  User professional;
  LocalDateTime createdAt;
  LocalDateTime editedAt;
  LocalDateTime scheduledFor;
  Room room;
  String observation;
  User createdBy;
  boolean isSupervisioned;
  boolean isConfirmed;
  boolean isPaid;

  public Appointment() {}

  public Appointment(
      User patient,
      User professional,
      LocalDateTime createdAt,
      LocalDateTime editedAt,
      LocalDateTime scheduledFor,
      Room room,
      String observation,
      User createdBy,
      boolean isSupervisioned,
      boolean isConfirmed,
      boolean isPaid) {
    this.patient = patient;
    this.professional = professional;
    this.createdAt = createdAt;
    this.editedAt = editedAt;
    this.scheduledFor = scheduledFor;
    this.room = room;
    this.observation = observation;
    this.createdBy = createdBy;
    this.isSupervisioned = isSupervisioned;
    this.isConfirmed = isConfirmed;
    this.isPaid = isPaid;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public User getPatient() {
    return patient;
  }

  public void setPatient(User patient) {
    this.patient = patient;
  }

  public User getProfessional() {
    return professional;
  }

  public void setProfessional(User professional) {
    this.professional = professional;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getEditedAt() {
    return editedAt;
  }

  public void setEditedAt(LocalDateTime editedAt) {
    this.editedAt = editedAt;
  }

  public LocalDateTime getScheduledFor() {
    return scheduledFor;
  }

  public void setScheduledFor(LocalDateTime scheduledFor) {
    this.scheduledFor = scheduledFor;
  }

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public boolean isSupervisioned() {
    return isSupervisioned;
  }

  public void setSupervisioned(boolean supervisioned) {
    isSupervisioned = supervisioned;
  }

  public boolean isConfirmed() {
    return isConfirmed;
  }

  public void setConfirmed(boolean confirmed) {
    isConfirmed = confirmed;
  }

  public boolean isPaid() {
    return isPaid;
  }

  public void setPaid(boolean paid) {
    isPaid = paid;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Appointment{");
    sb.append("id=").append(id);
    sb.append(", patient=").append(patient);
    sb.append(", professional=").append(professional);
    sb.append(", createdAt=").append(createdAt);
    sb.append(", editedAt=").append(editedAt);
    sb.append(", scheduledFor=").append(scheduledFor);
    sb.append(", room=").append(room);
    sb.append(", observation='").append(observation).append('\'');
    sb.append(", createdBy=").append(createdBy);
    sb.append(", isSupervisioned=").append(isSupervisioned);
    sb.append(", isConfirmed=").append(isConfirmed);
    sb.append(", isPaid=").append(isPaid);
    sb.append('}');
    return sb.toString();
  }
}
