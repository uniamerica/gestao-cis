package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.HealthInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HealthInsuranceRepository extends JpaRepository<HealthInsurance, Long> {

  Optional<HealthInsurance> findByInsuranceName(String name);
}
