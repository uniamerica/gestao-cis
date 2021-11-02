package com.gestaocis.backend.controllers;

import com.gestaocis.backend.DTOs.PatientDTOs.PatientResponseDTO;
import com.gestaocis.backend.services.PatientService;
import com.gestaocis.backend.util.PatientCreator;
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

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class PatientControllerTest {
  @InjectMocks private PatientsController patientController;

  @Mock private PatientService patientServiceMock;

  @BeforeEach
  void setUp() throws Exception {

    BDDMockito.when(patientServiceMock.save(ArgumentMatchers.any()))
        .thenReturn(PatientCreator.createValidPatientResponseDTOSaved());

    BDDMockito.when(patientServiceMock.findByUUID(ArgumentMatchers.any()))
        .thenReturn(PatientCreator.createValidPatientResponseDTOSaved());

    BDDMockito.when(patientServiceMock.findAll())
        .thenReturn(List.of(PatientCreator.createValidPatientResponseDTOSaved()));

    BDDMockito.when(patientServiceMock.update(ArgumentMatchers.any(), ArgumentMatchers.any()))
        .thenReturn(PatientCreator.createValidPatientResponseDTOUpdated());

    BDDMockito.when(patientServiceMock.delete(ArgumentMatchers.any())).thenReturn(true);
  }

  @Test
  @DisplayName("create Returns created Patient DTO when successful")
  public void create_returnCreatedPatientDTO_WhenSuccessful() throws Exception {
    String email = PatientCreator.createValidPatientResponseDTOSaved().getEmail();

    PatientResponseDTO response =
        patientController.save(PatientCreator.createValidPatientRequestDtoToBeSaved()).getBody();

    assertThat(response).isNotNull();
    assertThat(response.getEmail()).isEqualTo(email);
  }

  @Test
  @DisplayName("findAll Returns List of Patient DTO when successful")
  public void findAll_returnListOfPatientDTO_WhenSuccessful() {
    String email = PatientCreator.createValidPatientResponseDTOSaved().getEmail();

    List<PatientResponseDTO> response = patientController.findAll().getBody();

    assertThat(response).isNotNull().isNotEmpty().hasSize(1);
    assertThat(response.get(0).getEmail()).isEqualTo(email);
  }

  @Test
  @DisplayName("update Returns Patient DTO when successful")
  public void update_returnPatientDTO_WhenSuccessful() throws Exception {
    String cpf = PatientCreator.createValidPatientResponseDTOSaved().getCpf();

    PatientResponseDTO response =
        patientController
            .updatePatient(
                UUID.randomUUID(), PatientCreator.createValidPatientRequestDtoToBeSaved())
            .getBody();

    assertThat(response).isNotNull();
    assertThat(response.getCpf()).isEqualTo(cpf);
  }

  @Test
  @DisplayName("delete Returns Success or Error Message when successful")
  public void delete_returnSuccessOrErrorMessage_WhenSuccessful() {
    int value = patientController.deletePatient(UUID.randomUUID()).getStatusCode().value();

    assertThat(value).isNotNull().isEqualTo(200);
  }

  @Test
  @DisplayName("findByUUID Returns Patient DTO when successful")
  public void findByUUID_returnPatientDTO_WhenSuccessful() {
    String email = PatientCreator.createValidPatientResponseDTOSaved().getEmail();

    PatientResponseDTO response = patientController.findPatientByUUID(UUID.randomUUID()).getBody();

    assertThat(response).isNotNull();
    assertThat(response.getEmail()).isEqualTo(email);
  }

  @Test
  @DisplayName("findByEmail Returns Patient DTO when successful")
  public void findByEmail_returnPatientDTO_WhenSuccessful() {
    String email = PatientCreator.createValidPatientResponseDTOSaved().getEmail();

    PatientResponseDTO response =
        patientController.findPatientByEmail("integration@test.com").getBody();

    assertThat(response).isNotNull();
    assertThat(response.getEmail()).isEqualTo(email);
  }

  @Test
  @DisplayName("findByFullName Returns List of Patient DTO when successful")
  public void findByFullName_returnListOfPatientDTO_WhenSuccessful() {
    String fullName = PatientCreator.createValidPatientResponseDTOSaved().getFullName();

    List<PatientResponseDTO> response =
        patientController.findListOfPatientsByFullName("teste de paciente dodoi").getBody();
    System.out.println(response);

    assertThat(response).isNotNull().isNotEmpty().hasSize(1);
    //    assertThat(response.get(0).getEmail()).isEqualTo(email);
  }

  @Test
  @DisplayName("findByCpf Returns Patient DTO when successful")
  public void findByCpf_returnPatientDTO_WhenSuccessful() {
    String cpf = PatientCreator.createValidPatientResponseDTOSaved().getCpf();

    PatientResponseDTO response = patientController.findPatientByCpf("99999999999").getBody();

    assertThat(response).isNotNull();
    assertThat(response.getCpf()).isEqualTo(cpf);
  }
}
