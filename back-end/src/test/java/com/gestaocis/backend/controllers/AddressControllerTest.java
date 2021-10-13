package com.gestaocis.backend.controllers;

import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.services.AddressService;
import com.gestaocis.backend.util.AddressCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(SpringExtension.class)
public class AddressControllerTest {

  @InjectMocks private AddressController controller;

  @Mock private AddressService service;

  @BeforeEach
  void setUp() throws Exception {
    BDDMockito.when(service.listAll()).thenReturn(AddressCreator.createAddressList());

    BDDMockito.when(service.findByIdOrThrowResourceNotFoundException(ArgumentMatchers.anyLong()))
        .thenReturn(AddressCreator.createAddress(1));

    BDDMockito.when(service.findByCep(ArgumentMatchers.anyString()))
        .thenReturn(AddressCreator.createAddress(2));

    BDDMockito.when(service.findByStreet(ArgumentMatchers.anyString()))
        .thenReturn(AddressCreator.createAddress(2));

    BDDMockito.when(service.save(ArgumentMatchers.isA(Address.class)))
        .thenReturn(AddressCreator.createAddress(1));

    BDDMockito.doNothing().when(service).replace(ArgumentMatchers.isA(Address.class));

    BDDMockito.doNothing().when(service).delete(ArgumentMatchers.anyLong());
  }

  @Test
  @DisplayName("List all Address records")
  void find_listAllAddresses_whenSuccessful() throws Exception {

    String expectedStreet = AddressCreator.createAddressList().get(0).getStreet();
    System.out.println(expectedStreet);

    List<Address> addressList = controller.list().getBody();

    assertThat(addressList).isNotNull().isNotEmpty().hasSize(5);
    assertThat(addressList.get(0).getStreet()).isEqualTo(expectedStreet);
  }

  @Test
  @DisplayName("Find Address by id")
  void findById_returnsAddress_whenSuccessful() throws Exception {

    Long id = AddressCreator.createAddress(1).getId();

    Address fetchedAddress = controller.findById(1L).getBody();

    assertThat(fetchedAddress).isNotNull();
    assertThat(fetchedAddress.getId()).isNotNull().isEqualTo(id);
  }

  @Test
  @DisplayName("Find Address by cep")
  void findByCep_returnsAddress_whenSuccessful() throws Exception {

    String cep = AddressCreator.createAddress(2).getCep();

    Address fetchedAddress = controller.findByCep("85851-010").getBody();

    assertThat(fetchedAddress).isNotNull();
    assertThat(fetchedAddress.getCep()).isNotNull().isEqualTo(cep);
  }

  @Test
  @DisplayName("Find Address by street")
  void findByStreet_returnsAddress_whenSuccessful() throws Exception {

    String street = "rua almirante barroso";

    Address fetchedAddress = controller.findByStreet(street).getBody();

    assertThat(fetchedAddress).isNotNull().isEqualTo(AddressCreator.createAddress(2));
  }

  @Test
  @DisplayName("Should find an empty Address")
  void findByCep_noAddressByCep_whenSuccessful() throws Exception {

    BDDMockito.when(service.findByCep(ArgumentMatchers.anyString())).thenReturn(null);

    String cep = AddressCreator.createAddress(2).getCep();

    Address fetchedAddress = controller.findByCep("85851-010").getBody();

    assertThat(fetchedAddress).isNull();
  }

  @Test
  @DisplayName("Create Address record")
  void save_persistsAddress_whenSuccessful() throws Exception {

    Address savedAddress = controller.save(AddressCreator.createAddress(1)).getBody();

    assertThat(savedAddress).isNotNull().isEqualTo(AddressCreator.createAddress(1));
  }

  @Test
  @DisplayName("Replace Address record")
  void replace_updatesAddress_whenSuccessful() throws Exception {

    assertThatCode(() -> controller.replace(AddressCreator.createUpdatedAddress()))
        .doesNotThrowAnyException();

    ResponseEntity<Void> entity = controller.replace(AddressCreator.createUpdatedAddress());

    assertThat(entity).isNotNull();
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
  }

  @Test
  @DisplayName("Delete Address record")
  void delete_removesAddress_whenSuccessful() throws Exception {

    assertThatCode(() -> controller.delete(1L)).doesNotThrowAnyException();

    ResponseEntity<Void> entity = controller.delete(1L);

    assertThat(entity).isNotNull();
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
  }
}
