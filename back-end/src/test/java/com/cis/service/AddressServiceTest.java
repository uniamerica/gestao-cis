package com.cis.service;

import com.cis.exceptions.ResourceNotFoundException;
import com.cis.model.Address;
import com.cis.repository.AddressRepository;
import com.cis.util.AddressCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
@Log4j2
public class AddressServiceTest {

  @InjectMocks private AddressService service;

  @Mock private AddressRepository repository;

  @BeforeEach
  void setUp() throws Exception {
    BDDMockito.when(repository.findAll()).thenReturn(AddressCreator.createAddressList());

    BDDMockito.when(repository.findById(ArgumentMatchers.any(UUID.class)))
        .thenReturn(Optional.of(AddressCreator.createAddress(1)));

    BDDMockito.when(repository.findByCep(ArgumentMatchers.anyString()))
        .thenReturn(AddressCreator.createAddress(2));

    BDDMockito.when(repository.findByStreetIgnoreCase(ArgumentMatchers.anyString()))
        .thenReturn(AddressCreator.createAddress(2));

    BDDMockito.when(repository.save(ArgumentMatchers.isA(Address.class)))
        .thenReturn(AddressCreator.createAddress(2));

    BDDMockito.doNothing().when(repository).delete(ArgumentMatchers.any(Address.class));
  }

  @Test
  @DisplayName("List all Address records")
  void shouldListAllAddressRecordsWhenSuccessful() throws Exception {

    String expectedStreet = AddressCreator.createAddressList().get(0).getStreet();
    System.out.println(expectedStreet);

    List<Address> addressList = service.listAll();

    assertThat(addressList).isNotNull().isNotEmpty().hasSize(5);
    assertThat(addressList.get(0).getStreet()).isEqualTo(expectedStreet);
  }

  @Test
  @DisplayName("Find Address by id")
  void findById_returnsAddress_whenSuccessful() throws Exception {

    UUID id = AddressCreator.createAddress(1).getId();

    Address fetchedAddress =
        service.findByIdOrThrowResourceNotFoundException(
            UUID.fromString("9171004e-fb07-4cb1-bbbd-6010418481d5"));

    assertThat(fetchedAddress).isNotNull();
    assertThat(fetchedAddress.getId()).isNotNull().isEqualTo(id);
  }

  @Test
  @DisplayName("Find Address by Id - Throw ResourceNotFoundException")
  void findById_throwsResourceNotFoundException_whenIdIsNonexistent() throws Exception {

    BDDMockito.when(repository.findById(ArgumentMatchers.any(UUID.class)))
        .thenReturn(Optional.empty());

    Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
        .isThrownBy(() -> service.findByIdOrThrowResourceNotFoundException(UUID.randomUUID()));
  }

  @Test
  @DisplayName("Find Address by Cep")
  void findByCep_returnsAddress_whenSuccessful() throws Exception {

    String cep = CepService.formatCep("85851010");

    Address fetchedAddress = service.findByCep("85851010");

    assertThat(fetchedAddress).isNotNull();
    assertThat(fetchedAddress.getCep()).isNotNull().isEqualTo(cep);
  }

  @Test
  @DisplayName("Find Address by Street Name")
  void shouldFindAddressRecordByStreetWhenSuccessful() throws Exception {

    Address address = AddressCreator.createAddress(2);

    String street = "rua almirante barroso";

    Address fetchedAddress = service.findByStreet(street);

    assertAll(
        () -> assertThat(fetchedAddress).isNotNull(),
        () -> assertThat(address).isEqualTo(fetchedAddress));
  }

  @Test
  @DisplayName("Should find an empty Address")
  void shouldFindAddressRecordByCepWhenSuccessful() throws Exception {

    BDDMockito.when(repository.findByCep(ArgumentMatchers.anyString())).thenReturn(null);

    String cep = AddressCreator.createAddress(2).getCep();

    Address fetchedAddress = service.findByCep("85851-010");

    assertThat(fetchedAddress).isNull();
  }

  @Test
  @DisplayName("Create Address record")
  void shouldCreateAddressRecordWhenSuccessful() throws Exception {

    Address savedAddress = service.save(AddressCreator.createAddress(2));

    Address comparison =
        Address.builder()
            .id(UUID.fromString("9171004e-fb07-4cb1-bbbd-6010418481d6"))
            .neighborhood("Centro")
            .uf("PR")
            .city("Foz do Iguaçu")
            .street("Rua Almirante Barroso")
            .cep("85851-010")
            .build();

    assertThat(savedAddress).isNotNull().isEqualTo(comparison);
  }

  @Test
  @DisplayName("Update Address record")
  void shouldUpdateAddressRecordWhenSuccessful() throws Exception {

    assertThatCode(() -> service.update(AddressCreator.createUpdatedAddress()))
        .doesNotThrowAnyException();
  }

  @Test
  @DisplayName("Delete Address record")
  void shouldDeleteAddressRecordWhenSuccessful() throws Exception {

    assertThatCode(() -> service.delete(UUID.fromString("9171004e-fb07-4cb1-bbbd-6010418481d5")))
        .doesNotThrowAnyException();
  }
}
