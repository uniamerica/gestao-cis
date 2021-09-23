package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

  Address findByCep(String cep);

  Address findByUuid(UUID uuid);
}
