package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByProfessional(Long userId);
    List<Appointment> findByCreatedBy(Long userId);
    List<Appointment> findByPatient(Long userId);
    List<Appointment> findByScheduledForBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Appointment> findByRoom(Long roomId);
    Optional<Appointment> findByScheduledFor(LocalDateTime dateTime);

}
