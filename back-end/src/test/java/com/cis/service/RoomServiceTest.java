// package com.cis.service;
//
// import com.cis.exceptions.BadRequestException;
// import com.cis.model.Room;
// import com.cis.repository.RoomRepository;
// import com.cis.utils.RoomCreator;
// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.ArgumentMatchers;
// import org.mockito.BDDMockito;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
//
// import java.util.Optional;
// import java.util.UUID;
//
// @ExtendWith(SpringExtension.class)
// class RoomServiceTest {
//
//  @InjectMocks private RoomService roomService;
//
//  @Mock private RoomRepository roomRepository;
//
//  @BeforeEach
//  void setup() {
//    BDDMockito.when(roomRepository.findById(ArgumentMatchers.any()))
//        .thenReturn(Optional.of(RoomCreator.createRoomToBeSaved()));
//
//    BDDMockito.when(roomRepository.findByRoomNumber(ArgumentMatchers.any()))
//        .thenReturn(Optional.of(RoomCreator.createRoomToBeSaved()));
//
//    BDDMockito.doNothing().when(roomRepository).delete(ArgumentMatchers.any());
//  }
//
//  @Test
//  @DisplayName("Deve retornar profissional por ID quando bem-sucedido")
//  void test_findByUUID() {
//    Optional<Room> byUUID = roomService.findByIdOrThrowError(UUID.randomUUID());
//
//    Assertions.assertThat(byUUID).isNotNull();
//  }
//
//  @Test
//  @DisplayName("Deve mostrar bem-sucedido quanto criar uma sala")
//  void test_create() {
//
//    BDDMockito.when(roomRepository.findByRoomNumber(ArgumentMatchers.any()))
//        .thenReturn(Optional.empty());
//
//    Room room = roomService.create(RoomCreator.createRoomToBeSaved());
//
//    Assertions.assertThat(room).isNull();
//  }
//
//  @Test
//  @DisplayName("NÃO deve criar uma sala")
//  void test_createError() {
//
//    BDDMockito.when(roomRepository.save(ArgumentMatchers.any()))
//        .thenReturn(RoomCreator.createRoomToBeSaved());
//
//    Assertions.assertThatExceptionOfType(BadRequestException.class)
//        .isThrownBy(() -> roomService.create(RoomCreator.createRoomToBeSaved()));
//  }
//
//  @Test
//  @DisplayName("Deve atualizar a sala e aparecer bem-sucedido")
//  void test_update() {
//
//    BDDMockito.when(roomRepository.save(ArgumentMatchers.any()))
//        .thenReturn(RoomCreator.createRoomToBeSaved());
//
//    RoomResponseDTO update =
//        roomService.update(UUID.randomUUID(), RoomCreator.createRoomToBeSaved());
//
//    Assertions.assertThat(update).isNotNull();
//  }
//
//  @Test
//  @DisplayName("Deve excluir uma sala")
//  void test_delete() {
//    Assertions.assertThatCode(() -> roomService.delete(UUID.randomUUID()))
//        .doesNotThrowAnyException();
//  }
// }
