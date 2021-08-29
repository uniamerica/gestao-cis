package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.RoleEntity;
import com.gestaocis.backend.utils.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(Role role);
}
