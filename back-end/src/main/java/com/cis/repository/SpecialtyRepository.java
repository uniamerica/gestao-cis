package com.cis.repository;

import com.cis.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpecialtyRepository extends JpaRepository<Specialty, UUID> {

  Optional<Specialty> findByNameIgnoreCase(String name);
}
