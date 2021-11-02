package com.gestaocis.backend.controllers;

import com.gestaocis.backend.DTOs.SecretaryDTOs.SecretaryResponseDTO;
import com.gestaocis.backend.services.SecretaryService;
import com.gestaocis.backend.util.SecretaryCreator;
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
class SecretaryControllerTest {

  @InjectMocks private SecretaryController secretaryController;

  @Mock private SecretaryService secretaryServiceMock;

  @BeforeEach
  void setUp() throws Exception {

    BDDMockito.when(secretaryServiceMock.save(ArgumentMatchers.any()))
        .thenReturn(SecretaryCreator.createValidSecretaryResponseDTOSaved());

    BDDMockito.when(secretaryServiceMock.findByUUID(ArgumentMatchers.any()))
        .thenReturn(SecretaryCreator.createValidSecretaryResponseDTOSaved());

    BDDMockito.when(secretaryServiceMock.findByEmail(ArgumentMatchers.anyString()))
        .thenReturn(SecretaryCreator.createValidSecretaryResponseDTOSaved());

    BDDMockito.when(secretaryServiceMock.findByFullName(ArgumentMatchers.anyString()))
        .thenReturn(List.of(SecretaryCreator.createValidSecretaryResponseDTOSaved()));

    BDDMockito.when(secretaryServiceMock.findByRole())
        .thenReturn(List.of(SecretaryCreator.createValidSecretaryResponseDTOSaved()));

    BDDMockito.when(secretaryServiceMock.findByCpf(ArgumentMatchers.anyString()))
        .thenReturn(SecretaryCreator.createValidSecretaryResponseDTOSaved());

    BDDMockito.when(secretaryServiceMock.update(ArgumentMatchers.any(), ArgumentMatchers.any()))
        .thenReturn(SecretaryCreator.createValidSecretaryResponseDTOUpdated());

    BDDMockito.when(secretaryServiceMock.delete(ArgumentMatchers.any())).thenReturn(true);
  }

  @Test
  @DisplayName("Save Returns Saved Secretary DTO when successful")
  public void save_returnSavedSecretaryDTO_WhenSuccessful() throws Exception {
    String email = SecretaryCreator.createValidSecretaryResponseDTOSaved().getEmail();

    SecretaryResponseDTO response =
        secretaryController
            .save(SecretaryCreator.createValidSecretaryRequestDtoToBeSaved())
            .getBody();

    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getEmail()).isEqualTo(email);
  }

  @Test
  @DisplayName("findByUUID Returns Secretary DTO when successful")
  public void findByUUID_returnSecretaryDTO_WhenSuccessful() {
    String email = SecretaryCreator.createValidSecretaryResponseDTOSaved().getEmail();

    SecretaryResponseDTO response =
        secretaryController.findSecretaryByUUID(UUID.randomUUID()).getBody();

    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getEmail()).isEqualTo(email);
  }

  @Test
  @DisplayName("findByEmail Returns Secretary DTO when successful")
  public void findByEmail_returnSecretaryDTO_WhenSuccessful() {
    String email = SecretaryCreator.createValidSecretaryResponseDTOSaved().getEmail();

    SecretaryResponseDTO response =
        secretaryController.findSecretaryByEmail("sazhan@carai").getBody();

    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getEmail()).isEqualTo(email);
  }

  @Test
  @DisplayName("findByFullName Returns List of Secretary DTO when successful")
  public void findByFullName_returnListOfSecretaryDTO_WhenSuccessful() {
    String email = SecretaryCreator.createValidSecretaryResponseDTOSaved().getEmail();

    List<SecretaryResponseDTO> response =
        secretaryController
            .findListOfSecretariesByFullName("jiasdh8sahgdosiahjdp9ashdasoiud")
            .getBody();

    Assertions.assertThat(response).isNotNull().isNotEmpty().hasSize(1);
    Assertions.assertThat(response.get(0).getEmail()).isEqualTo(email);
  }

  @Test
  @DisplayName("findByRole Returns List of Secretary DTO when successful")
  public void findByRole_returnListOfSecretaryDTO_WhenSuccessful() {
    String email = SecretaryCreator.createValidSecretaryResponseDTOSaved().getEmail();

    List<SecretaryResponseDTO> response = secretaryController.findAllByRole().getBody();

    Assertions.assertThat(response).isNotNull().isNotEmpty().hasSize(1);
    Assertions.assertThat(response.get(0).getEmail()).isEqualTo(email);
  }

  @Test
  @DisplayName("findByCpf Returns Secretary DTO when successful")
  public void findByCpf_returnSecretaryDTO_WhenSuccessful() {
    String cpf = SecretaryCreator.createValidSecretaryResponseDTOSaved().getCpf();

    SecretaryResponseDTO response =
        secretaryController.findSecretaryByCpf("sazhan@carai").getBody();

    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getCpf()).isEqualTo(cpf);
  }

  @Test
  @DisplayName("update Returns Secretary DTO when successful")
  public void update_returnSecretaryDTO_WhenSuccessful() {
    String cpf = SecretaryCreator.createValidSecretaryResponseDTOSaved().getCpf();

    SecretaryResponseDTO response =
        secretaryController
            .updateSecretary(
                UUID.randomUUID(), SecretaryCreator.createValidSecretaryRequestDtoToBeSaved())
            .getBody();

    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getCpf()).isEqualTo(cpf);
  }

  @Test
  @DisplayName("delete Returns Success or Error Message when successful")
  public void delete_returnSuccessOrErrorMessage_WhenSuccessful() {
    int value = secretaryController.deleteSecretary(UUID.randomUUID()).getStatusCode().value();

    Assertions.assertThat(value).isNotNull().isEqualTo(200);
  }
}
