package com.cis.controller;

import com.cis.model.dto.HeathProfessionalDTO.HealthProfessionalResponseDTO;
import com.cis.service.HealthProfessionalService;
import com.cis.utils.HealthProfessionalCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class HealthProfessionalControllerTest {

    @InjectMocks
    private HealthProfessionalController healthProfessionalController;

    @Mock
    private HealthProfessionalService healthProfessionalService;

    @Test
    @DisplayName("Should create Health Professional When Successful")
    public void test_create() throws Exception {
        BDDMockito.when(healthProfessionalService.create(ArgumentMatchers.any()))
                .thenReturn(HealthProfessionalCreator.createHealthProfessionalDTOSaved());

        ResponseEntity<HealthProfessionalResponseDTO> healthProfessionalResponseDTOResponseEntity = healthProfessionalController.create(HealthProfessionalCreator.createHealthProfessionalDTOToBeSaved());

        Assertions.assertThat(healthProfessionalResponseDTOResponseEntity).isNotNull();
        Assertions.assertThat(healthProfessionalResponseDTOResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(healthProfessionalResponseDTOResponseEntity.getBody().getEmail()).isEqualTo(HealthProfessionalCreator.createHealthProfessionalDTOSaved().getEmail());
    }

    @Test
    @DisplayName("Should return Health Professional When Successful")
    public void test_findById() throws Exception {
        BDDMockito.when(healthProfessionalService.findById(ArgumentMatchers.any()))
                .thenReturn(HealthProfessionalCreator.createHealthProfessionalDTOSaved());

        ResponseEntity<HealthProfessionalResponseDTO> healthProfessionalResponseDTOResponseEntity = healthProfessionalController.show(UUID.randomUUID());

        Assertions.assertThat(healthProfessionalResponseDTOResponseEntity).isNotNull();
        Assertions.assertThat(healthProfessionalResponseDTOResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(healthProfessionalResponseDTOResponseEntity.getBody().getEmail()).isEqualTo(HealthProfessionalCreator.createHealthProfessionalDTOSaved().getEmail());
    }

    @Test
    @DisplayName("Should update Health Professional When Successful")
    public void test_update() throws Exception {
        var creator = HealthProfessionalCreator.createHealthProfessionalDTOSaved();
        creator.setPhone("7896548");

        BDDMockito.when(healthProfessionalService.update(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(creator);


        ResponseEntity<HealthProfessionalResponseDTO> update = healthProfessionalController.update(UUID.randomUUID(), HealthProfessionalCreator.createHealthProfessionalDTOToBeSaved());

        Assertions.assertThat(update).isNotNull();
        Assertions.assertThat(update.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThat(update.getBody().getPhone()).isEqualTo(creator.getPhone());
    }

    @Test
    @DisplayName("Should delete HealthProfessional When Successful")
    public void test_delete(){
        BDDMockito.doNothing().when(healthProfessionalService).delete(ArgumentMatchers.any());
        ResponseEntity<String> delete = healthProfessionalController.delete(UUID.randomUUID());

        Assertions.assertThat(delete).isNotNull();
        Assertions.assertThat(delete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}