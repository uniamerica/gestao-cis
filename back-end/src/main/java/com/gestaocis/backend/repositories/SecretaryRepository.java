package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SecretaryRepository extends JpaRepository<Secretary, UUID> {
}
