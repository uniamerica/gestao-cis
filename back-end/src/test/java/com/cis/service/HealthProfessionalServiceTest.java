package com.cis.service;

import com.cis.exceptions.BadRequestException;
import com.cis.model.dto.HeathProfessionalDTO.HealthProfessionalResponseDTO;
import com.cis.repository.HealthProfessionalRepository;
import com.cis.utils.HealthProfessionalCreator;
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

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class HealthProfessionalServiceTest {

    @InjectMocks
    private HealthProfessionalService healthProfessionalService;

    @Mock
    private HealthProfessionalRepository healthProfessionalRepository;


    @BeforeEach
    void setup(){
        BDDMockito.when(healthProfessionalRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(HealthProfessionalCreator.createHealthProfessionalSaved()));

        BDDMockito.when(healthProfessionalRepository.save(ArgumentMatchers.any()))
                .thenReturn(HealthProfessionalCreator.createHealthProfessionalSaved());

        BDDMockito.doNothing().when(healthProfessionalRepository).delete(ArgumentMatchers.any());

    }

    @Test
    @DisplayName("Should return Professional by ID when successful")
     void test_findById(){
        HealthProfessionalResponseDTO byId = healthProfessionalService.findById(UUID.randomUUID());

        Assertions.assertThat(byId).isNotNull();
        Assertions.assertThat(byId.getEmail()).isEqualTo(HealthProfessionalCreator.createHealthProfessionalSaved().getEmail());
    }

    @Test
    @DisplayName("Should create Health Professional when successful")
    void test_create(){
        BDDMockito.when(healthProfessionalRepository.findByEmail(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        HealthProfessionalResponseDTO healthProfessionalResponseDTO = healthProfessionalService.create(HealthProfessionalCreator.createHealthProfessionalDTOToBeSaved());

        Assertions.assertThat(healthProfessionalResponseDTO).isNotNull();
        Assertions.assertThat(healthProfessionalResponseDTO.getEmail()).isEqualTo(HealthProfessionalCreator.createHealthProfessionalSaved().getEmail());

    }

    @Test
    @DisplayName("Should NOT create Health Professional when successful")
    void test_createError(){
        BDDMockito.when(healthProfessionalRepository.findByEmail(ArgumentMatchers.any()))
                .thenReturn(Optional.of(HealthProfessionalCreator.createHealthProfessionalSaved()));

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> healthProfessionalService.create(HealthProfessionalCreator.createHealthProfessionalDTOToBeSaved()));

    }

    @Test
    @DisplayName("Should update Health Professional when successful")
    void test_update(){
        HealthProfessionalResponseDTO update = healthProfessionalService.update(UUID.randomUUID(), HealthProfessionalCreator.createHealthProfessionalDTOToBeSaved());

        Assertions.assertThat(update).isNotNull();

    }

    @Test
    @DisplayName("Should delete Health Professional when successful")
    void test_delete(){
        Assertions.assertThatCode(() -> healthProfessionalService.delete(UUID.randomUUID())).doesNotThrowAnyException();
    }

}