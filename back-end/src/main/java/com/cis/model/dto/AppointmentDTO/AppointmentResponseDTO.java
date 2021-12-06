package com.cis.model.dto.AppointmentDTO;

import com.cis.model.Appointment;
import com.cis.model.dto.HeathProfessionalDTO.HealthProfessionalResponseDTO;
import com.cis.model.dto.PatientDTO.PatientReturnDTO;
import com.cis.model.dto.RoomDTO.RoomResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentResponseDTO {
  private UUID id;
  private Date date;
  private Integer hour;
  private Integer minute;
  private RoomResponseDTO room;
  private HealthProfessionalResponseDTO professional;
  private PatientReturnDTO patient;
  private String observation;
  private Boolean paid;
  private Double fee;

  public AppointmentResponseDTO(Appointment appointment) {
    this.id = appointment.getId();
    this.date = appointment.getDate();
    this.hour = appointment.getHour();
    this.minute = appointment.getMinute();
    this.room = new RoomResponseDTO(appointment.getRoom());
    this.professional = new HealthProfessionalResponseDTO(appointment.getProfessional());
    this.patient = new PatientReturnDTO(appointment.getPatient());
    this.observation = appointment.getObservation();
    this.paid = appointment.getPaid();
    this.fee = appointment.getFee();
  }
}
