package com.cis.repository;

import com.cis.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

  Address findByCep(String cep);

  Address findByStreetIgnoreCase(String street);

  Address findByStreetContaining(String streetPattern);
}
