package com.gestaocis.backend.integration;

import com.gestaocis.backend.BackEndApplication;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.util.AddressCreator;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
    classes = BackEndApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Log4j2
class AddressControllerIntegrationTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  void shouldListAllAddressRecords() throws Exception {
    ResponseEntity<Address[]> responseEntity =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/api/v1/addresses", Address[].class);

    Address[] addresses = responseEntity.getBody();

    assertAll(() -> assertNotNull(addresses), () -> assertEquals(3, addresses.length));
  }

  @Test
  void shouldFindAddressById() {}

  @Test
  void shouldSaveAddressRecord() throws Exception {
    Address address = AddressCreator.createAddress(2);
    log.info(address.toString());

    ResponseEntity<Address> responseEntity =
        this.restTemplate.postForEntity(
            "http://localhost:" + port + "/api/v1/addresses", address, Address.class);

    log.info(responseEntity.toString());

    assertEquals(201, responseEntity.getStatusCodeValue());
  }
}
