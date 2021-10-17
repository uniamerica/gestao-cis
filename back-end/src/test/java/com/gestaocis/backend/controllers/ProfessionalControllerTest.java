package com.gestaocis.backend.controllers;

import com.gestaocis.backend.DTOs.ProfessionalDTOs.ProfessionalResponseDTO;
import com.gestaocis.backend.services.ProfessionalService;
import com.gestaocis.backend.util.ProfessionalCreator;
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
public class ProfessionalControllerTest {
    @InjectMocks
    private ProfessionalController professionalController;
    
    @Mock
    private ProfessionalService professionalServiceMock;
    
    @BeforeEach
    void setUp() throws Exception {
        BDDMockito.when(professionalServiceMock.save(ArgumentMatchers.any()))
                .thenReturn(ProfessionalCreator.createValidProfessionalResponseDTOSaved());

        BDDMockito.when(professionalServiceMock.findByUUID(ArgumentMatchers.any()))
                .thenReturn(ProfessionalCreator.createValidProfessionalResponseDTOSaved());

        BDDMockito.when(professionalServiceMock.findAll())
                .thenReturn(List.of(ProfessionalCreator.createValidProfessionalResponseDTOSaved()));

        BDDMockito.when(professionalServiceMock.update(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(ProfessionalCreator.createValidProfessionalResponseDTOUpdated());

        BDDMockito.when(professionalServiceMock.delete(ArgumentMatchers.any()))
                .thenReturn(true);
    }

    @Test
    @DisplayName("create Returns created Professional DTO when successful")
    public void create_returnCreatedProfessionalDTO_WhenSuccessful() throws Exception {
        String email = ProfessionalCreator.createValidProfessionalResponseDTOSaved().getEmail();

        ProfessionalResponseDTO response = professionalController.save(ProfessionalCreator.createValidProfessionalRequestDtoToBeSaved()).getBody();


        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getEmail()).isEqualTo(email);

    }

    @Test
    @DisplayName("findByUUID Returns Professional DTO when successful")
    public void findByUUID_returnProfessionalDTO_WhenSuccessful(){
        String email = ProfessionalCreator.createValidProfessionalResponseDTOSaved().getEmail();

        ProfessionalResponseDTO response = professionalController.findProfessionalByUUID(UUID.randomUUID()).getBody();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getEmail()).isEqualTo(email);

        // return "Professional founded by UUID!";
    }

    @Test
    @DisplayName("update Returns Professional DTO when successful")
    public void update_returnProfessionalDTO_WhenSuccessful() throws Exception {
        String cpf = ProfessionalCreator.createValidProfessionalResponseDTOSaved().getCpf();

        ProfessionalResponseDTO response = professionalController.updateProfessional(UUID.randomUUID(), ProfessionalCreator.createValidProfessionalRequestDtoToBeSaved()).getBody();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getCpf()).isEqualTo(cpf);
    }

    @Test
    @DisplayName("delete Returns Success or Error Message when successful")
    public void delete_returnSuccessOrErrorMessage_WhenSuccessful(){
        int value = professionalController.deleteProfessional(UUID.randomUUID()).getStatusCode().value();

        Assertions.assertThat(value).isNotNull().isEqualTo(200);

    }
}
