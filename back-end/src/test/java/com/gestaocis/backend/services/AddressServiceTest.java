package com.gestaocis.backend.services;

import com.gestaocis.backend.exceptions.ResourceNotFoundException;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.repositories.AddressRepository;
import com.gestaocis.backend.util.AddressCreator;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(SpringExtension.class)
public class AddressServiceTest {

  @InjectMocks private AddressService service;

  @Mock private AddressRepository repository;

  @BeforeEach
  void setUp() throws Exception {
    BDDMockito.when(repository.findAll()).thenReturn(AddressCreator.createAddressList());

    BDDMockito.when(repository.findById(ArgumentMatchers.anyLong()))
        .thenReturn(Optional.of(AddressCreator.createAddress(1)));

    BDDMockito.when(repository.findByCep(ArgumentMatchers.anyString()))
        .thenReturn(AddressCreator.createAddress(2));

    BDDMockito.when(repository.save(ArgumentMatchers.isA(Address.class)))
        .thenReturn(AddressCreator.createAddress(3));

    BDDMockito.doNothing().when(repository).delete(ArgumentMatchers.any(Address.class));
  }

  @Test
  @DisplayName("List all Address records")
  void find_listAllAddresses_whenSuccessful() throws Exception {

    String expectedStreet = AddressCreator.createAddressList().get(0).getStreet();
    System.out.println(expectedStreet);

    List<Address> addressList = service.listAll();

    assertThat(addressList).isNotNull().isNotEmpty().hasSize(5);
    assertThat(addressList.get(0).getStreet()).isEqualTo(expectedStreet);
  }

  @Test
  @DisplayName("Find Address by id")
  void findById_returnsAddress_whenSuccessful() throws Exception {

    Long id = AddressCreator.createAddress(1).getId();

    Address fetchedAddress = service.findByIdOrThrowResourceNotFoundException(1L);

    assertThat(fetchedAddress).isNotNull();
    assertThat(fetchedAddress.getId()).isNotNull().isEqualTo(id);
  }

  @Test
  @DisplayName("Find Address by id - Throw ResourceNotFoundException")
  void findById_throwsResourceNotFoundException_whenIdIsNonexistent() throws Exception {

    BDDMockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
        .isThrownBy(() -> service.findByIdOrThrowResourceNotFoundException(1L));
  }

  @Test
  @DisplayName("Find Address by cep")
  void findByCep_returnsAddress_whenSuccessful() throws Exception {

    String cep = AddressCreator.createAddress(2).getCep();

    Address fetchedAddress = service.findByCep("85851-010");

    assertThat(fetchedAddress).isNotNull();
    assertThat(fetchedAddress.getCep()).isNotNull().isEqualTo(cep);
  }

  @Test
  @DisplayName("Should find an empty Address")
  void findByCep_noAddressByCep_whenSuccessful() throws Exception {

    BDDMockito.when(repository.findByCep(ArgumentMatchers.anyString())).thenReturn(null);

    String cep = AddressCreator.createAddress(2).getCep();

    Address fetchedAddress = service.findByCep("85851-010");

    assertThat(fetchedAddress).isNull();
  }

  // Ajustar
  @Test
  @DisplayName("Create Address record")
  void save_persistsAddress_whenSuccessful() throws Exception {

    Address savedAddress = service.save(AddressCreator.createAddress(3));

    assertThat(savedAddress).isNotNull().isEqualTo(AddressCreator.createAddress(3));
  }

  @Test
  @DisplayName("Replace Address record")
  void replace_updatesAddress_whenSuccessful() throws Exception {

    assertThatCode(() -> service.replace(AddressCreator.createUpdatedAddress()))
        .doesNotThrowAnyException();
  }

  @Test
  @DisplayName("Delete Address record")
  void delete_removesAddress_whenSuccessful() throws Exception {

    assertThatCode(() -> service.delete(1L)).doesNotThrowAnyException();
  }
}
