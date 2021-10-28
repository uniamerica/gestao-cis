package com.gestaocis.backend.controllers;

import com.gestaocis.backend.models.Room;
import com.gestaocis.backend.services.RoomService;
import com.gestaocis.backend.util.RoomCreator;
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

@ExtendWith(SpringExtension.class)
public class RoomControllerTest {
  @InjectMocks private RoomController controller;

  @Mock private RoomService service; // clone

  @BeforeEach
  void setUp() {
    BDDMockito.when(service.listAll()).thenReturn(RoomCreator.createRoomList());

    BDDMockito.when(service.findById(ArgumentMatchers.anyLong()))
        .thenReturn(RoomCreator.createOptionalRoomToBeSaved());

    BDDMockito.when(service.save(ArgumentMatchers.isA(Room.class)))
        .thenReturn(RoomCreator.createRoomToBeSaved());

    BDDMockito.doNothing().when(service).update(ArgumentMatchers.isA(Room.class));

    BDDMockito.doNothing().when(service).delete(ArgumentMatchers.anyLong());

    BDDMockito.when(service.findByRoomNumber(ArgumentMatchers.anyInt()))
        .thenReturn(RoomCreator.createOptionalRoomToBeSaved());
  }

  @Test
  @DisplayName("Lista de salas")
  void listRooms() {
    Integer numeroEsperadoSala = RoomCreator.createRoomList().get(0).getRoomNumber(); //Pegando o numero o 1 elemento lista

    List<Room> roomList = controller.list().getBody();  //Usando o controller para listar as salas

    Assertions.assertThat(roomList).isNotNull().isNotEmpty().hasSize(3);  //certeza q não é nula e tem 3 registros

    Assertions.assertThat(roomList.get(0).getRoomNumber()).isEqualTo(numeroEsperadoSala);  //verificando se é o esperado
  }

  @Test
  @DisplayName("Achar sala por ID")
  void findById() {



  }

}
