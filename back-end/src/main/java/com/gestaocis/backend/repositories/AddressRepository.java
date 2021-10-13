package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

  Address findByCep(String cep);

  Address findByStreetIgnoreCase(String street);

  Address findByStreetContaining(String streetPattern);
}
