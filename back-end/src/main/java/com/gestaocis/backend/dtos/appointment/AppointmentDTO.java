package com.gestaocis.backend.dtos.appointment;

import com.gestaocis.backend.dtos.user.UserPatientDTO;
import com.gestaocis.backend.dtos.user.UserProfessionalDTO;
import com.gestaocis.backend.models.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDTO {
    private UUID uuid;
    private UserPatientDTO patient;
    private UserProfessionalDTO professional;
    private LocalDateTime createdAt;
    private LocalDateTime scheduledFor;

    public AppointmentDTO(Appointment appointment){
        uuid = appointment.getUuid();
        patient = new UserPatientDTO(appointment.getPatient());
        professional = new UserProfessionalDTO(appointment.getProfessional());
        createdAt = appointment.getCreatedAt();
        scheduledFor = appointment.getScheduledFor();
    }

}
