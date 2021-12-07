package com.cis.integration;

import com.cis.CisApplication;
import com.cis.model.Address;
import com.cis.repository.AddressRepository;
import com.cis.service.CepService;
import com.cis.util.AddressCreator;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
    classes = CisApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Log4j2
class AddressControllerIntegrationTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Autowired private AddressRepository repository;

  @BeforeEach
  void setUp() throws Exception {
    repository.saveAll(AddressCreator.createAddressList());
  }

  @Test
  void shouldListAllAddressRecords() throws Exception {
    ResponseEntity<Address[]> responseEntity =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/api/addresses", Address[].class);

    Address[] addresses = responseEntity.getBody();

    assertAll(() -> assertNotNull(addresses), () -> assertEquals(5, addresses.length));
  }

  @Test
  void shouldFindAddressById() {
    List<Address> addresses = repository.findAll();

    UUID id = addresses.get(addresses.size() - 1).getId();

    ResponseEntity<Address> responseEntity =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/api/addresses/" + id, Address.class);

    Address address = responseEntity.getBody();

    assertAll(
        () -> assertNotNull(address),
        () -> {
          assert address != null;
          assertEquals(id, address.getId());
        });
  }

  //  @Test
  //  void shouldFindAddressByStreetName() {
  //    List<Address> addresses = repository.findAll();
  //
  //    String street = "rua almirante barroso";
  //
  //    ResponseEntity<Address> responseEntity =
  //        this.restTemplate.getForEntity(
  //            "http://localhost:"
  //                + port
  //                + "/api/addresses/searchByStreetContaining/?street="
  //                + street,
  //            Address.class);
  //
  //    Address address = responseEntity.getBody();
  //
  //    assertNotNull(address);
  //  }

  @Test
  void shouldFindAddressByCep() {
    String cep = "04849529";

    ResponseEntity<Address> responseEntity =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/api/addresses/search/?cep=" + cep, Address.class);

    Address address = responseEntity.getBody();
    System.out.println(address);

    assertAll(
        () -> assertNotNull(address),
        () -> assertEquals(CepService.formatCep(cep), address.getCep()));
  }

  @Test
  void shouldSaveAddressRecord() throws Exception {
    Address address = AddressCreator.createAddress(1);

    ResponseEntity<Address> responseEntity =
        this.restTemplate.postForEntity(
            "http://localhost:" + port + "/api/addresses", address, Address.class);

    log.info(responseEntity.toString());

    assertEquals(201, responseEntity.getStatusCodeValue());
  }

  @Test
  void shouldDeleteAddressRecord() {

    List<Address> addresses = repository.findAll();

    Address lastAddress = addresses.get(addresses.size() - 1);

    UUID id = lastAddress.getId();

    this.restTemplate.delete("http://localhost:" + port + "/api/addresses/" + id);

    assertEquals(4, repository.findAll().size());
  }

  @Test
  void shouldUpdateAddressRecord() throws Exception {
    List<Address> addresses = repository.findAll();

    Address lastAddress = addresses.get(addresses.size() - 1);
    log.info(lastAddress);

    UUID id = lastAddress.getId();

    Address newAddressFields = AddressCreator.createUpdatedAddress();

    this.restTemplate.put("http://localhost:" + port + "/api/addresses/" + id, newAddressFields);

    Assertions.assertAll(
        () -> assertNotEquals(lastAddress.getNeighborhood(), newAddressFields.getNeighborhood()),
        () -> assertNotEquals(lastAddress.getCity(), newAddressFields.getCity()),
        () -> assertNotEquals(lastAddress.getStreet(), newAddressFields.getStreet()));
  }

  @AfterEach
  void tearDown() {
    repository.deleteAll();
  }
}
