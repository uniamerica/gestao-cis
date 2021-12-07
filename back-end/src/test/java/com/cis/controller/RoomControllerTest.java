//package com.cis.controller;
//
//import com.cis.model.Room;
//import com.cis.service.RoomService;
//import com.cis.utils.RoomCreator;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.BDDMockito;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//
//@ExtendWith(SpringExtension.class)
//public class RoomControllerTest {
//    @InjectMocks
//    private RoomController controller;
//
//    @Mock
//    private RoomService service; // clone
//
//    @BeforeEach
//    void setUp() {
//        BDDMockito.when(service.listAll()).thenReturn(RoomCreator.createRoomList());
//
//        BDDMockito.when(service.findByIdOrThrowError(ArgumentMatchers.any()))
//                .thenReturn(RoomCreator.createOptionalRoomToBeSaved());
//
//        BDDMockito.when(service.save(ArgumentMatchers.any()))
//                .thenReturn(RoomCreator.createRoomToBeSaved());
//
//        BDDMockito.when(service.update(ArgumentMatchers.any(),ArgumentMatchers.any()))
//                .thenReturn(RoomCreator.createValidRoomResponseDTOSaved());
//
//        BDDMockito.doNothing().when(service).delete(ArgumentMatchers.any());
//
//        BDDMockito.when(service.findByNumberOrThrowError(ArgumentMatchers.any()))
//                .thenReturn(RoomCreator.createOptionalRoomToBeSaved());
//    }
//
//    @Test
//    @DisplayName("Lista de salas")
//    void listRooms() {
//        String numeroEsperadoSala = RoomCreator.createRoomList().get(0).getRoomNumber(); //Pegando o numero o 1 elemento lista
//
//        List<Room> roomList = controller.list().getBody();  //Usando o controller para listar as salas
//
//        Assertions.assertThat(roomList).isNotNull().isNotEmpty().hasSize(3);  //certeza q não é nula e tem 3 registros
//
//        Assertions.assertThat(roomList.get(0).getRoomNumber()).isEqualTo(numeroEsperadoSala);  //verificando se é o esperado
//    }
//
//    @Test
//    @DisplayName("Achar sala por ID")
//    void findByUUID() {
//
//    }
//
//}