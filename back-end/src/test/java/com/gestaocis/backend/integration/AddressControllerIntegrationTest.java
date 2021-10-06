package com.gestaocis.backend.integration;

import com.gestaocis.backend.BackEndApplication;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.repositories.AddressRepository;
import com.gestaocis.backend.services.CepService;
import com.gestaocis.backend.util.AddressCreator;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
    classes = BackEndApplication.class,
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
            "http://localhost:" + port + "/api/v1/addresses", Address[].class);

    Address[] addresses = responseEntity.getBody();

    assertAll(() -> assertNotNull(addresses), () -> assertEquals(5, addresses.length));
  }

  @Test
  void shouldFindAddressById() {
    List<Address> addresses = repository.findAll();

    Address lastAddress = addresses.get(addresses.size() - 1);

    int id = lastAddress.getId().intValue();

    ResponseEntity<Address> responseEntity =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/api/v1/addresses/id/" + id, Address.class);

    Address address = responseEntity.getBody();

    assertAll(() -> assertNotNull(address), () -> assertEquals(id, address.getId()));
  }

  @Test
  void shouldFindAddressByCep() {
    String cep = CepService.formatCep("85851010");

    ResponseEntity<Address> responseEntity =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/api/v1/addresses/cep/" + cep, Address.class);

    Address address = responseEntity.getBody();

    assertAll(() -> assertNotNull(address), () -> assertEquals(cep, address.getCep()));
  }

  @Test
  void shouldSaveAddressRecord() throws Exception {
    Address address = AddressCreator.createAddress(1);

    ResponseEntity<Address> responseEntity =
        this.restTemplate.postForEntity(
            "http://localhost:" + port + "/api/v1/addresses", address, Address.class);

    log.info(responseEntity.toString());

    assertEquals(201, responseEntity.getStatusCodeValue());
  }

  @Test
  void shouldDeleteAddressRecord() {

    List<Address> addresses = repository.findAll();

    Address lastAddress = addresses.get(addresses.size() - 1);

    int id = lastAddress.getId().intValue();

    this.restTemplate.delete("http://localhost:" + port + "/api/v1/addresses/" + id);

    assertEquals(4, repository.findAll().size());
  }

  @Test
  void shouldUpdateAddressRecord() throws Exception {
    List<Address> addresses = repository.findAll();

    Address lastAddress = addresses.get(addresses.size() - 1);
    log.info(lastAddress);

    int id = lastAddress.getId().intValue();

    Address newAddressFields = AddressCreator.createUpdatedAddress();
    log.info(newAddressFields);

    this.restTemplate.put("http://localhost:" + port + "/api/v1/addresses/" + id, newAddressFields);

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
