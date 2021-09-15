package com.gestaocis.backend.services;

import com.gestaocis.backend.models.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AppointmentService {
  Appointment save(Appointment any);

  Optional<Appointment> getById(Long id);

  void delete(Appointment appointment);

  Appointment update(Appointment appointment);

  Page<Appointment> find(Appointment filter, Pageable pageRequest);
}
