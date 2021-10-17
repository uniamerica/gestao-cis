package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfessionalRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUuid(UUID uuid);
    Optional<User> findByCpf(String cpf);
    List<User> findByFullNameContainingIgnoreCase(String name);

}
