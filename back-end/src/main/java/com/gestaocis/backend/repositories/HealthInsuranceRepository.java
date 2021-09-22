package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.HealthInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthInsuranceRepository extends JpaRepository<HealthInsurance, Long> {
}
