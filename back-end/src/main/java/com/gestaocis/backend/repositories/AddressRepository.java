package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, Long> {

  Optional<Address> findByUuid(UUID uuid);

  Optional<Address> findByCep(String cep);
}
