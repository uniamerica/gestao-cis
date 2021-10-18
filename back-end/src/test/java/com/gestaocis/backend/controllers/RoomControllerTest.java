package com.gestaocis.backend.controllers;

import com.gestaocis.backend.DTOs.RoomDTOs.RoomResponseDTO;
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
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class RoomControllerTest {
    @InjectMocks
    private RoomController roomController;

    @Mock
    private RoomService roomServiceMock;

    @BeforeEach
    void setUp() throws Exception {
        BDDMockito.when(roomServiceMock.save(ArgumentMatchers.any()))
                .thenReturn(RoomCreator.createValidRoomResponseDTOSaved());

        BDDMockito.when(roomServiceMock.findByUUID(ArgumentMatchers.any()))
                .thenReturn(RoomCreator.createValidRoomResponseDTOSaved());

        BDDMockito.when(roomServiceMock.findAll())
                .thenReturn(List.of(RoomCreator.createValidRoomResponseDTOSaved()));

        BDDMockito.when(roomServiceMock.update(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(RoomCreator.createValidRoomResponseDTOUpdated());

        BDDMockito.when(roomServiceMock.delete(ArgumentMatchers.any()))
                .thenReturn(true);
    }

    @Test
    @DisplayName("create Returns created Room DTO when successful")
    public void create_returnCreatedRoomDTO_WhenSuccessful() throws Exception {
        String email = RoomCreator.createValidRoomResponseDTOSaved().getEmail();

        RoomResponseDTO response = roomController.save(RoomCreator.createValidRoomRequestDtoToBeSaved()).getBody();


        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getEmail()).isEqualTo(email);

    }

    @Test
    @DisplayName("findByUUID Returns Room DTO when successful")
    public void findByUUID_returnRoomDTO_WhenSuccessful(){
        String email = RoomCreator.createValidRoomResponseDTOSaved().getEmail();

        RoomResponseDTO response = roomController.findRoomByUUID(UUID.randomUUID()).getBody();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getEmail()).isEqualTo(email);

        // return "Room founded by UUID!";
    }

    @Test
    @DisplayName("update Returns Room DTO when successful")
    public void update_returnRoomDTO_WhenSuccessful() throws Exception {
        String cpf = RoomCreator.createValidRoomResponseDTOSaved().getCpf();

        RoomResponseDTO response = roomController.updateRoom(UUID.randomUUID(), RoomCreator.createValidRoomRequestDtoToBeSaved()).getBody();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getCpf()).isEqualTo(cpf);
    }

    @Test
    @DisplayName("delete Returns Success or Error Message when successful")
    public void delete_returnSuccessOrErrorMessage_WhenSuccessful(){
        int value = roomController.deleteRoom(UUID.randomUUID()).getStatusCode().value();

        Assertions.assertThat(value).isNotNull().isEqualTo(200);

    }
}
}
