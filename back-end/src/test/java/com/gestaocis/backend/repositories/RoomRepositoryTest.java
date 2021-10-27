package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Room;
import com.gestaocis.backend.util.RoomCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RoomRepositoryTest {

  @Autowired private RoomRepository repository;

  @Test
  @DisplayName("Salvar um registro de sala")
  void saveRoom() {
    Room salaParaSalvar = RoomCreator.createRoomToBeSaved(); // criando o obj sala

    Room salaSalva = this.repository.save(salaParaSalvar); // salvando a sala

    assertNotNull(salaSalva); // verificando se não é nulo
    assertNotNull(salaSalva.getId()); // id nao é nulo
    assertEquals( // verificar se 2 dados são iguais
        salaParaSalvar.getRoomNumber(), salaSalva.getRoomNumber()); // os numeros sao equivalentes
  }

  @Test
  @DisplayName("Update registro de sala")
  void updateRoom() {
    Room salaParaSalvar = RoomCreator.createRoomToBeSaved(); // criando o obj sala

    Room salaSalva = this.repository.save(salaParaSalvar); // salvando a sala

    salaSalva.setRoomNumber(06); // atualizando registro

    Room salaAtualizada = this.repository.save(salaSalva); // salvar o registro atualizado

    assertEquals(06, salaAtualizada.getRoomNumber());
  }

  @Test
  @DisplayName("Delete de registro de sala")
  void deleteRoom() {
    Room salaParaSalvar = RoomCreator.createRoomToBeSaved(); // criando o obj sala

    Room salaSalva = this.repository.save(salaParaSalvar); // salvando a sala

    this.repository.delete(salaSalva);

    Optional<Room> salaDeletada = this.repository.findByRoomNumber(02);

    assertEquals(Optional.empty(), salaDeletada);
  }

  @Test
  @DisplayName("Achar uma sala por numero")
  void findRoomByNumber() {
    Room salaParaSalvar = RoomCreator.createRoomToBeSaved(); // criando o obj sala

    Room salaSalva = this.repository.save(salaParaSalvar); // salvando a sala

    Optional<Room> salaBusca = this.repository.findByRoomNumber(salaSalva.getRoomNumber());

    assertTrue(salaBusca.isPresent()); // para verificar q o salaBusca existe
    assertEquals(salaParaSalvar.getRoomNumber(), salaBusca.get().getRoomNumber());
    assertEquals(salaSalva.getId(), salaBusca.get().getId());
  }

  @Test
  @DisplayName("Lista de salas")
  void listRooms() {
    List<Room> salasParaSalvar = RoomCreator.createRoomList(); // criando para salvar

    this.repository.saveAll(salasParaSalvar); // salvando

    List<Room> salasListadas = this.repository.findAll(); // buscando todos registros de salas

    assertNotNull(salasListadas);
    assertEquals(salasParaSalvar, salasListadas);
    assertEquals(3, salasListadas.size()); // comparando quantidade de registros no banco de dados
  }

  @Test
  @DisplayName("Achar sala por ID")
  void findById() {
    Room salaParaSalvar = RoomCreator.createRoomToBeSaved(); // criando o obj sala

    Room salaSalva = this.repository.save(salaParaSalvar); // salvando a sala

    Optional<Room> salaBuscaId = this.repository.findById(salaSalva.getId());

    assertTrue(salaBuscaId.isPresent());
    assertEquals(salaSalva.getId(), salaBuscaId.get().getId());
  }
}
