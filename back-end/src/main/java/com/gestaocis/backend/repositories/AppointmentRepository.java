package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Appointment;
import com.gestaocis.backend.models.Room;
import com.gestaocis.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByProfessional(User user);
    List<Appointment> findByCreatedBy(User user);
    List<Appointment> findByPatient(User user);
    List<Appointment> findByScheduledForBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Appointment> findByRoom(Room room);
    Optional<Appointment> findByScheduledFor(LocalDateTime dateTime);

}
