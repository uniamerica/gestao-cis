package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.services.CepService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class AddressRepositoryTest {

  @Autowired private AddressRepository repository;

  @Test
  void shouldCreateAddressRecord() throws Exception {

    String cepToBeSaved = "85857600";

    Address address = CepService.convertCepToAddress(cepToBeSaved);

    System.out.println(address);

    Address address1 = repository.save(address);

    Assertions.assertThat(CepService.formatCep(cepToBeSaved)).isEqualTo(address1.getCep());
    Assertions.assertThat(address1).isNotNull();
  }

  @Test
  void shouldListAllAddressRecords() throws Exception {
    String cepToBeSaved = "85857600";

    Address address = CepService.convertCepToAddress(cepToBeSaved);

    Address address1 = repository.save(address);

    List<Address> addresses = repository.findAll();

    Assertions.assertThat(addresses).isNotEmpty();
  }
}
