package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Room;
import com.gestaocis.backend.util.RoomCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RoomRepositoryTest {

  @Autowired private RoomRepository repository;

  @Test
  void saveRoom() {
    Room salaParaSalvar = RoomCreator.createRoomToBeSaved(); // criando o obj sala

    Room salaSalva = this.repository.save(salaParaSalvar); // salvando a sala

    assertNotNull(salaSalva); // verificando se não é nulo
    assertNotNull(salaSalva.getId()); // id nao é nulo
    assertEquals( // verificar se 2 dados são iguais
        salaParaSalvar.getRoomNumber(), salaSalva.getRoomNumber()); // os numeros sao equivalentes
  }

  @Test
  void updateRoom() {
    Room salaParaSalvar = RoomCreator.createRoomToBeSaved(); // criando o obj sala

    Room salaSalva = this.repository.save(salaParaSalvar); // salvando a sala

    salaSalva.setRoomNumber(06); // atualizando registro

    Room salaAtualizada = this.repository.save(salaSalva); // salvar o registro atualizado

    assertEquals(06, salaAtualizada.getRoomNumber());
  }

  @Test
  void deleteRoom() {
    Room salaParaSalvar = RoomCreator.createRoomToBeSaved(); // criando o obj sala

    Room salaSalva = this.repository.save(salaParaSalvar); // salvando a sala

    this.repository.delete(salaSalva);

    Optional<Room> salaDeletada = this.repository.findByRoomNumber(02);

    assertEquals(Optional.empty(), salaDeletada);
  }

  @Test
  void findRoomByNumber() {
    Room salaParaSalvar = RoomCreator.createRoomToBeSaved(); // criando o obj sala

    Room salaSalva = this.repository.save(salaParaSalvar); // salvando a sala

    Optional<Room> salaBusca = this.repository.findByRoomNumber(salaSalva.getRoomNumber());

    assertTrue(salaBusca.isPresent()); // para verificar q o salaBusca existe
    assertEquals(salaParaSalvar.getRoomNumber(), salaBusca.get().getRoomNumber());
    assertEquals(salaSalva.getId(), salaBusca.get().getId());
  }

  @Test
  void listRooms() {

  }
}
