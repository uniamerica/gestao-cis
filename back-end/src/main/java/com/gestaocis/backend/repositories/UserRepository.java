package com.gestaocis.backend.repositories;

import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findByUuid(UUID uuid);

  Optional<User> findByCpf(String cpf);

  Optional<User> findByCpfAndRole(String cpf, RoleEntity role);

  List<User> findByFullNameContainingIgnoreCase(String name);

  List<User> findByRole(RoleEntity role);
}
