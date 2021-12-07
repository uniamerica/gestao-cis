package com.cis.repository;

import com.cis.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

  Optional<Patient> findByEmailIgnoreCase(String email);
}
