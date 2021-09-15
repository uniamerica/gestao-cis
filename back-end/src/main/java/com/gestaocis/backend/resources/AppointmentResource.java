package com.gestaocis.backend.resources;

import com.gestaocis.backend.dtos.appointment.AppointmentDTOPost;
import com.gestaocis.backend.dtos.user.UserPatientDTO;
import com.gestaocis.backend.dtos.user.UserProfessionalDTO;
import com.gestaocis.backend.models.Room;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentResource {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AppointmentDTOPost create() {

    AppointmentDTOPost dto =
        AppointmentDTOPost.builder()
            .patient(new UserPatientDTO())
            .professional(new UserProfessionalDTO())
            .scheduledFor(LocalDateTime.of(2021, 9, 6, 9, 30, 0, 0))
            .room(new Room())
            .observation("")
            .supervised(false)
            .paid(true)
            .build();
    return dto;
  }
}
