package com.gestaocis.backend.services;

import com.gestaocis.backend.exceptions.InconsistentDataException;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.repositories.AddressRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

  @Mock private AddressRepository repository;

  @InjectMocks private AddressService service;

  @Test
  @DisplayName("Create Address record")
  void shouldCreateAddressRecord() throws Exception {
    Address address = CepService.convertCepToAddress("85857600");

    Mockito.when(repository.save(any(Address.class))).thenReturn(address);

    Address savedAddress = repository.save(address);
    Assertions.assertThat(savedAddress.getStreet()).isNotNull();
  }

  @Test
  @DisplayName("Find Address record in database")
  public void shouldFindAddressInDatabase() throws Exception {
    Address address = CepService.convertCepToAddress("85857600");

    List<Address> addresses = new ArrayList<>();
    addresses.add(address);

    Mockito.when(repository.findAll()).thenReturn(addresses);

    List<Address> fetchedAddresses = service.listAll();
    Assertions.assertThat(fetchedAddresses.size()).isGreaterThan(0);
  }

  @Test
  @DisplayName("Throw error due to inconsistent CEP submission")
  public void shouldThrowInconsistentDataException() {
    org.junit.jupiter.api.Assertions.assertThrows(
        InconsistentDataException.class,
        () -> {
          String cep = "abcdef";
          service.findByCep(cep);
        });
  }
}
