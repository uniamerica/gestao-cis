package com.gestaocis.backend.repositories;

import com.gestaocis.backend.enums.SpecialtyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialtyEntityRepository extends JpaRepository<SpecialtyEntity, Long> {

    Optional<SpecialtyEntity> findBySpecialtyNameIgnoreCase(String name);
}
