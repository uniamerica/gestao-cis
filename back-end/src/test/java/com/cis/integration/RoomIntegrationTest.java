package com.cis.integration;

import com.cis.model.Room;
import com.cis.model.dto.RoomDTO.RoomResponseDTO;
import com.cis.repository.RoomRepository;
import com.cis.utils.RoomCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class RoomIntegrationTest {

  @Autowired private TestRestTemplate testRestTemplate;

  @Autowired private RoomRepository roomRepository;

  @BeforeEach
  void setup() {}

  @Test
  @DisplayName("Teste de Integração Salas - CREATE")
  public void test_create() {
    ResponseEntity<RoomResponseDTO> create =
        testRestTemplate.exchange(
            "/api/room",
            HttpMethod.POST,
            new HttpEntity<>(RoomCreator.createRoomToBeSaved()),
            RoomResponseDTO.class);

    Assertions.assertThat(create).isNotNull();
    Assertions.assertThat(create.getStatusCode()).isEqualTo(HttpStatus.CREATED);
  }

  @Test
  @DisplayName("Teste de Integração Salas - FIND BY ID")
  public void test_findById() {
    Room saved = roomRepository.save(RoomCreator.createRoomToBeSaved());

    ResponseEntity<RoomResponseDTO> findById =
        testRestTemplate.exchange(
            "/api/room/{id}", HttpMethod.GET, null, RoomResponseDTO.class, saved.getId());
    Assertions.assertThat(findById).isNotNull();
    Assertions.assertThat(findById.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  @DisplayName("Teste de Integração Salas - UPDATE")
  public void test_update() {
    Room saved = roomRepository.save(RoomCreator.createRoomToBeSaved());

    saved.setRoomNumber("456789456");

    ResponseEntity<Void> update =
        testRestTemplate.exchange(
            "/api/room/{id}", HttpMethod.PUT, new HttpEntity<>(saved), Void.class, saved.getId());
    Assertions.assertThat(update).isNotNull();
    Assertions.assertThat(update.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
  }

  @Test
  @DisplayName("Teste de Integração Salas - DELETE")
  public void test_delete() {
    Room saved = roomRepository.save(RoomCreator.createRoomToBeSaved());

    ResponseEntity<String> delete =
        testRestTemplate.exchange(
            "/api/room/{id}", HttpMethod.DELETE, null, String.class, saved.getId());
    Assertions.assertThat(delete).isNotNull();
    Assertions.assertThat(delete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
  }
}
