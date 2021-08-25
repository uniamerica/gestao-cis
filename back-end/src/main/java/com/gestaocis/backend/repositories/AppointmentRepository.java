package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
