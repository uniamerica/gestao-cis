package com.cis.repository;

import com.cis.model.Address;
import com.cis.service.CepService;
import com.cis.util.AddressCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("AddressRepository Tests")
public class AddressRepositoryTest {

  @Autowired private AddressRepository repository;

  @Test
  @DisplayName("Create Address Record")
  void shouldCreateAddressRecordWhenSuccessful() throws Exception {
    Address addressToBeSaved = AddressCreator.createAddress(3);

    Address savedAddress = this.repository.save(addressToBeSaved);

    assertAll(
        "Test Address record's data",
        () -> assertNotNull(savedAddress),
        () -> assertNotNull(savedAddress.getId()),
        () -> assertEquals(CepService.formatCep(addressToBeSaved.getCep()), savedAddress.getCep()),
        () -> assertEquals(addressToBeSaved.getStreet(), savedAddress.getStreet()),
        () -> assertEquals(addressToBeSaved.getCity(), savedAddress.getCity()),
        () -> assertEquals(addressToBeSaved.getUf(), savedAddress.getUf()),
        () -> assertEquals(addressToBeSaved.getNeighborhood(), savedAddress.getNeighborhood()));
  }

  @Test
  @DisplayName("Update Address record")
  void shouldUpdateAddressRecordWhenSuccessful() throws Exception {

    Address addressToBeSaved = AddressCreator.createAddressNoId(1);

    Address savedAddress = repository.save(addressToBeSaved);

    savedAddress.setStreet("Rua Ipanema");
    savedAddress.setCity("Toledo");
    savedAddress.setNeighborhood("Conjunto Libra");

    Address updatedAddress = repository.save(savedAddress);

    assertAll(
        "Test Address record's data",
        () -> assertNotNull(updatedAddress),
        () -> assertNotNull(updatedAddress.getId()),
        () -> assertEquals(CepService.formatCep("85857600"), updatedAddress.getCep()),
        () -> assertEquals("Rua Ipanema", updatedAddress.getStreet()),
        () -> assertEquals("Toledo", updatedAddress.getCity()),
        () -> assertEquals("PR", updatedAddress.getUf()),
        () -> assertEquals("Conjunto Libra", updatedAddress.getNeighborhood()));
  }

  @Test
  @DisplayName("Delete Address record")
  void shouldDeleteAddressRecordWhenSuccessful() throws Exception {

    Address addressToBeSaved = AddressCreator.createAddress(1);

    Address savedAddress = this.repository.save(addressToBeSaved);

    this.repository.delete(savedAddress);

    Address deletedAddress =
        this.repository.findByCep(CepService.formatCep(addressToBeSaved.getCep()));

    assertThat(deletedAddress).isNull();
  }

  @Test
  @DisplayName("Find an Address record by CEP")
  void shouldFindAddressRecordByCepWhenSuccessful() throws Exception {

    Address addressToBeSaved = AddressCreator.createAddress(2);

    Address savedAddress = this.repository.save(addressToBeSaved);

    Address retrievedAddress = this.repository.findByCep(savedAddress.getCep());

    assertAll(
        "Test Address record's data",
        () ->
            assertEquals(addressToBeSaved.getCep(), retrievedAddress.getCep(), "CEP did not match"),
        () ->
            assertEquals(
                addressToBeSaved.getStreet(), retrievedAddress.getStreet(), "Street did not match"),
        () ->
            assertEquals(
                addressToBeSaved.getCity(), retrievedAddress.getCity(), "City did not match"),
        () -> assertEquals(addressToBeSaved.getUf(), retrievedAddress.getUf(), "UF did not match"),
        () ->
            assertEquals(
                addressToBeSaved.getNeighborhood(),
                retrievedAddress.getNeighborhood(),
                "Neighborhood did not match"));
  }

  @Test
  @DisplayName("Find an Address record by Street name")
  void shouldFindAddressRecordByStreetWhenSuccessful() throws Exception {
    Address addressToBeSaved = AddressCreator.createAddress(1);

    Address savedAddress = this.repository.save(addressToBeSaved);

    Address retrievedAddress = this.repository.findByStreetIgnoreCase(addressToBeSaved.getStreet());

    assertAll(
        () -> assertNotNull(retrievedAddress), () -> assertEquals(savedAddress, retrievedAddress));
  }

  @Test
  @DisplayName("Should find an empty Address")
  void shouldFindAnEmptyAddressRecordWhenSuccessful() {

    Address address = this.repository.findByCep(CepService.formatCep("85851010"));

    assertThat(address).isNull();
  }

  @Test
  @DisplayName("List all Address records")
  void shouldListAllAddressRecordsWhenSuccessful() throws Exception {

    List<Address> addressesToBeSaved = AddressCreator.createAddressList();

    this.repository.saveAll(addressesToBeSaved);

    List<Address> returnedAddresses = this.repository.findAll();

    assertAll(
        "Test Addresses records' data",
        () -> assertFalse(returnedAddresses.isEmpty()),
        () -> assertThat(returnedAddresses).containsAll(addressesToBeSaved));
  }
}
