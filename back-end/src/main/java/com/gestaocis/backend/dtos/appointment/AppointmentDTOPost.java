package com.gestaocis.backend.dtos.appointment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gestaocis.backend.dtos.user.UserPatientDTO;
import com.gestaocis.backend.dtos.user.UserProfessionalDTO;
import com.gestaocis.backend.models.Appointment;
import com.gestaocis.backend.models.Room;
import com.gestaocis.backend.serializers.LocalDateTimeDeserializer;
import com.gestaocis.backend.serializers.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDTOPost {

  private UserPatientDTO patient;
  private UserProfessionalDTO professional;

  //  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime scheduledFor;

  private Room room;
  private String observation;
  private boolean supervised;
  private boolean paid;

  public AppointmentDTOPost(Appointment appointment) {
    patient = new UserPatientDTO(appointment.getPatient());
    professional = new UserProfessionalDTO(appointment.getProfessional());
    scheduledFor = appointment.getScheduledFor();
    room = appointment.getRoom();
    observation = appointment.getObservation();
    supervised = appointment.isSupervised();
    paid = appointment.isPaid();
  }
}
