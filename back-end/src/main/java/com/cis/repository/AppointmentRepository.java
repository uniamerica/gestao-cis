package com.cis.repository;

import com.cis.model.Appointment;
import com.cis.model.HealthProfessional;
import com.cis.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
  Optional<Appointment> findByDate(Date date);

  Optional<Appointment> findByHour(Integer hour);

  Optional<Appointment> findByMinute(Integer minute);

  Optional<Appointment> findByProfessionalId(UUID professional_id);

  Optional<Appointment> findByRoomId(UUID room_id);

  Optional<Appointment> findByPatientId(UUID patient_id);

  Optional<Appointment> findAppointmentByDateAndAndHourAndMinuteAndPatientAndProfessional(
      Date date, Integer hour, Integer minute, Patient patient, HealthProfessional professional);
}
