package com.cis.controller;

import com.cis.model.dto.ScheduleDTO.ScheduleResponseDTO;
import com.cis.service.ScheduleService;
import com.cis.utils.ScheduleCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class ScheduleControllerTest {

    @InjectMocks
    private ScheduleController scheduleController;

    @Mock
    private ScheduleService scheduleService;

    @Test
    @DisplayName("Should return Page of Schedule when successful")
    public void test_index(){
        PageImpl<ScheduleResponseDTO> scheduleResponseDTOPage = new PageImpl<>(List.of(ScheduleCreator.createValidScheduleResponseDTOSaved()));

        BDDMockito.when(scheduleService.index(ArgumentMatchers.any()))
                .thenReturn(scheduleResponseDTOPage);

        ResponseEntity<Page<ScheduleResponseDTO>> index = scheduleController.index(null);

        Assertions.assertThat(index).isNotNull();
        Assertions.assertThat(index.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(index.getBody().toList())
                .hasSize(1)
                .isNotNull();

    }

    @Test
    @DisplayName("Should create Schedule when successful")
    public void test_create(){
        BDDMockito.when(scheduleService.create(ArgumentMatchers.any()))
                .thenReturn(ScheduleCreator.createValidScheduleResponseDTOSaved());

        ResponseEntity<ScheduleResponseDTO> scheduleResponseDTOResponseEntity = scheduleController.create(ScheduleCreator.createValidScheduleRequestDTOtoBeSaved());

        Assertions.assertThat(scheduleResponseDTOResponseEntity).isNotNull();
        Assertions.assertThat(scheduleResponseDTOResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("Should find Schedule by id when successful")
    public void test_findById(){
        BDDMockito.when(scheduleService.findByID(ArgumentMatchers.any()))
                .thenReturn(ScheduleCreator.createValidScheduleResponseDTOSaved());

        ResponseEntity<ScheduleResponseDTO> byId = scheduleController.findById(UUID.randomUUID());

        Assertions.assertThat(byId).isNotNull();
        Assertions.assertThat(byId.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @DisplayName("Should update Schedule when successful")
    public void test_update(){
        BDDMockito.when(scheduleService.update(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(ScheduleCreator.createValidScheduleResponseDTOSaved());

        ResponseEntity<ScheduleResponseDTO> update = scheduleController.update(UUID.randomUUID(), ScheduleCreator.createValidScheduleRequestDTOtoBeSaved());

        Assertions.assertThat(update).isNotNull();
        Assertions.assertThat(update.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Should delete when successful")
    public void test_deleteById(){
       BDDMockito.doNothing().when(scheduleService).delete(ArgumentMatchers.any());

        ResponseEntity<String> stringResponseEntity = scheduleController.deleteById(UUID.randomUUID());

        Assertions.assertThat(stringResponseEntity).isNotNull();
        Assertions.assertThat(stringResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Should delete all By Professional when successful")
    public void test_deleteAllByProfessionalId(){
        BDDMockito.doNothing().when(scheduleService).deleteAllByProfessional(ArgumentMatchers.any());

        ResponseEntity<String> stringResponseEntity = scheduleController.deleteAllByProfessionalId(UUID.randomUUID());

        Assertions.assertThat(stringResponseEntity).isNotNull();
        Assertions.assertThat(stringResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Should delete all By Room when successful")
    public void test_deleteAllByRoomId(){
        BDDMockito.doNothing().when(scheduleService).deleteAllByRoom(ArgumentMatchers.any());

        ResponseEntity<String> stringResponseEntity = scheduleController.deleteAllByRoomId(UUID.randomUUID());

        Assertions.assertThat(stringResponseEntity).isNotNull();
        Assertions.assertThat(stringResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}