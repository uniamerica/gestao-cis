package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.SpecialtyEntity;
import com.gestaocis.backend.utils.enums.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpecialtyEntityRepository extends JpaRepository<SpecialtyEntity, Long> {

    Optional<SpecialtyEntity> findBySpecialtyName(Specialty specialty);
}
