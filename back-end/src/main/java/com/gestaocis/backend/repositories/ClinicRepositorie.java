package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClinicRepositorie extends JpaRepository<Clinic, UUID> {
}
