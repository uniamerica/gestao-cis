package com.cis.model.dto.AppointmentDTO;

import com.cis.model.Patient;
import com.cis.model.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentRequestDTO {
  private UUID patientId;
  private UUID scheduleId;
  private String observation;
  private Boolean paid;
  private Double fee;

  public AppointmentRequestDTO(Schedule schedule, Patient patient) {
    this.patientId = patient.getId();
    this.scheduleId = schedule.getId();
  }
}
