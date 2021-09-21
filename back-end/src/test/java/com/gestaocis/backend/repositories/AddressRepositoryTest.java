package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.services.CepService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class AddressRepositoryTest {

  @Autowired private AddressRepository repository;

  @BeforeEach
  void setUp() throws Exception {

    String[] ceps = {"85851010", "85851-210", "85851000", "85852-000", "85851110"};

    List<Address> addresses = new ArrayList<>();

    for (String cep : ceps) {
      addresses.add(CepService.convertCepToAddress(cep));
    }

    repository.saveAll(addresses);
  }

  //  @Disabled
  @Test
  @DisplayName("Create Address record")
  void shouldCreateAddressRecord() throws Exception {

    String[] data = {
      "85857600", "Rua Ipanema Conjunto Libra", "Foz do Iguaçu", "PR", "Campos do Iguaçu"
    };

    Address address = CepService.convertCepToAddress(data[0]);

    Address address1 = repository.save(address);

    org.junit.jupiter.api.Assertions.assertAll(
        "Test Address record's data",
        () -> org.junit.jupiter.api.Assertions.assertNotNull(address1),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                CepService.formatCep(data[0]), address1.getCep()),
        () -> org.junit.jupiter.api.Assertions.assertEquals(data[1], address1.getStreet()),
        () -> org.junit.jupiter.api.Assertions.assertEquals(data[2], address1.getCity()),
        () -> org.junit.jupiter.api.Assertions.assertEquals(data[3], address1.getUf()),
        () -> org.junit.jupiter.api.Assertions.assertEquals(data[4], address1.getNeighborhood()));
  }

  @Test
  @DisplayName("List all Address records")
  void shouldListAllAddressRecords() {

    String[] addressesData = {"85851010", "Rua Almirante Barroso", "Foz do Iguaçu", "PR", "Centro"};

    String[] addressesData1 = {
      "85851210", "Avenida Juscelino Kubitschek", "Foz do Iguaçu", "PR", "Centro"
    };
    String[] addressesData2 = {"85851000", "Avenida Brasil", "Foz do Iguaçu", "PR", "Centro"};

    String[] addressesData3 = {
      "85852000",
      "Avenida ParanáCentro/Vila Residencial A/Jardim das Laranjeiras",
      "Foz do Iguaçu",
      "PR",
      "Centro"
    };

    String[] addressesData4 = {
      "85851110", "Avenida Jorge Schimmelpfeng", "Foz do Iguaçu", "PR", "Centro"
    };

    List<Address> addresses = repository.findAll();

    System.out.println(addresses);

    org.junit.jupiter.api.Assertions.assertAll(
        "Test Addresses records' data",
        () -> org.junit.jupiter.api.Assertions.assertFalse(addresses.isEmpty()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                CepService.formatCep(addressesData[0]), addresses.get(0).getCep()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData[1], addresses.get(0).getStreet()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData[2], addresses.get(0).getCity()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData[3], addresses.get(0).getUf()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData[4], addresses.get(0).getNeighborhood()),
        () -> org.junit.jupiter.api.Assertions.assertFalse(addresses.isEmpty()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                CepService.formatCep(addressesData1[0]), addresses.get(1).getCep()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData1[1], addresses.get(1).getStreet()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData1[2], addresses.get(1).getCity()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData1[3], addresses.get(1).getUf()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData1[4], addresses.get(1).getNeighborhood()),
        () -> org.junit.jupiter.api.Assertions.assertFalse(addresses.isEmpty()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                CepService.formatCep(addressesData2[0]), addresses.get(2).getCep()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData2[1], addresses.get(2).getStreet()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData2[2], addresses.get(2).getCity()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData2[3], addresses.get(2).getUf()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData2[4], addresses.get(2).getNeighborhood()),
        () -> org.junit.jupiter.api.Assertions.assertFalse(addresses.isEmpty()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                CepService.formatCep(addressesData3[0]), addresses.get(3).getCep()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData3[1], addresses.get(3).getStreet()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData3[2], addresses.get(3).getCity()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData3[3], addresses.get(3).getUf()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData3[4], addresses.get(3).getNeighborhood()),
        () -> org.junit.jupiter.api.Assertions.assertFalse(addresses.isEmpty()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                CepService.formatCep(addressesData4[0]), addresses.get(4).getCep()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData4[1], addresses.get(4).getStreet()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData4[2], addresses.get(4).getCity()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData4[3], addresses.get(4).getUf()),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                addressesData4[4], addresses.get(4).getNeighborhood()));
  }

  @Test
  @DisplayName("Find an Address record by CEP")
  void shouldFindAddressByCep() {

    String cep = CepService.formatCep("85851010");

    String[] data = {"Rua Almirante Barroso", "Foz do Iguaçu", "PR", "Centro"};

    Address address = repository.findByCep(cep);

    System.out.println(address);

    Assertions.assertThat(cep).isEqualTo(address.getCep());

    org.junit.jupiter.api.Assertions.assertAll(
        "Test Address record's data",
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                cep, address.getCep(), "CEP did not match"),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                data[0], address.getStreet(), "Street did not match"),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                data[1], address.getCity(), "City did not match"),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                data[2], address.getUf(), "UF did not match"),
        () ->
            org.junit.jupiter.api.Assertions.assertEquals(
                data[3], address.getNeighborhood(), "Neighborhood did not match"));
  }
}
