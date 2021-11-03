package com.gestaocis.backend.integration;

import com.gestaocis.backend.DTOs.PatientDTOs.NewPatientRequestDTO;
import com.gestaocis.backend.DTOs.PatientDTOs.PatientResponseDTO;
import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.AddressRepository;
import com.gestaocis.backend.repositories.RoleEntityRepository;
import com.gestaocis.backend.repositories.UserRepository;
import com.gestaocis.backend.util.PatientCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class PatientIntegrationTest {

  @Autowired private TestRestTemplate testRestTemplate;

  @Autowired private RoleEntityRepository roleEntityRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private AddressRepository addressRepository;

  private UUID uuid;

  @BeforeEach
  void setUp() throws Exception {
    RoleEntity role =
        roleEntityRepository.save(RoleEntity.builder().roleName(Role.ROLE_PATIENT).build());

    Address address =
        addressRepository.save(
            Address.builder()
                .cep("85853330")
                .street("rua acacio pedroso")
                .city("foz do iguaçu")
                .uf("parana")
                .neighborhood("bairro yolanda")
                .build());

    User userToBeSaved = PatientCreator.createPatientToBeSaved();
    userToBeSaved.setAddress(address);
    userToBeSaved.setRole(role);
    userRepository.save(userToBeSaved);

    uuid = userToBeSaved.getUuid();
  }

  @Test
  @DisplayName("save Returns Saved Patient DTO when successful")
  public void save_returnsSavedPatientDto_whenSuccessful() {

    NewPatientRequestDTO patientToBeCreatedDTO =
        NewPatientRequestDTO.builder()
            .cpf("99999999991")
            .rg("88888888881")
            .email("integrationtest@test.com")
            .phone("(45)999666999")
            .fullName("Paciente Dodói da Silva")
            .birthdate("1993-12-29")
            .sex('F')
            .addressCountry("Brazel")
            .addressLine2("Rua da minha casa")
            .password("senha123")
            .cep("85858150")
            .street("Rua teste")
            .city("Foz do Iguaçu")
            .uf("PR")
            .neighborhood("Bairro da minha casa")
            .build();

    ResponseEntity<PatientResponseDTO> response =
        testRestTemplate.postForEntity(
            "/api/pacientes", patientToBeCreatedDTO, PatientResponseDTO.class);

    assertThat(response).isNotNull();

    assertThat(response.getStatusCode().value()).isEqualTo(201);
    assertThat(Objects.requireNonNull(response.getBody()).getEmail())
        .isEqualTo(patientToBeCreatedDTO.getEmail());
  }

  @Test
  @DisplayName("findByUuid returns PatientDTO when successful")
  public void findByUuid_returnsPatientDTO_whenSuccessful() {
    ResponseEntity<PatientResponseDTO> response =
        testRestTemplate.exchange(
            "/api/pacientes/id/{uuid}", HttpMethod.GET, null, PatientResponseDTO.class, uuid);

    assertThat(response).isNotNull();

    assertThat(response.getStatusCode().value()).isEqualTo(200);

    assertThat(Objects.requireNonNull(response.getBody()).getUuid()).isEqualTo(uuid);
  }

  @Test
  @DisplayName("findByEmail returns PatientDTO when successful")
  public void findByEmail_returnsPatientDTO_whenSuccessful() {
    String email = PatientCreator.createValidPatientResponseDTOSaved().getEmail();

    ResponseEntity<PatientResponseDTO> response =
        testRestTemplate.getForEntity("/api/pacientes/email/" + email, PatientResponseDTO.class);

    assertThat(response).isNotNull();

    assertThat(response.getStatusCode().value()).isEqualTo(200);

    assertThat(Objects.requireNonNull(response.getBody()).getEmail()).isEqualTo(email);
  }

  @Test
  @DisplayName("findByName returns list of PatientDTO when successful")
  public void findByName_returnsListOfPatientDTO_whenSuccessful() {
    String email = PatientCreator.createValidPatientResponseDTOSaved().getEmail();

    String name = PatientCreator.createValidPatientResponseDTOSaved().getFullName();

    ResponseEntity<PatientResponseDTO[]> response =
        testRestTemplate.getForEntity("/api/pacientes/name/" + name, PatientResponseDTO[].class);

    assertThat(response).isNotNull();

    assertThat(response.getStatusCode().value()).isEqualTo(200);
  }

  @Test
  @DisplayName("findAll Returns List of Patients DTO when successful")
  public void findAll_returnListOfPatientDTO_WhenSuccessful() {
    ResponseEntity<PatientResponseDTO[]> response =
        testRestTemplate.getForEntity("/api/pacientes", PatientResponseDTO[].class);

    assertThat(response).isNotNull();

    assertThat(response.getStatusCode().value()).isEqualTo(200);
  }

  @Test
  @DisplayName("findByCpf returns Patient DTO when successful")
  public void findByCpf_returnsPatientDTO_whenSuccessful() {
    String cpf = PatientCreator.createPatientToBeSaved().getCpf();

    ResponseEntity<PatientResponseDTO> response =
        testRestTemplate.getForEntity("/api/pacientes/cpf/" + cpf, PatientResponseDTO.class);

    assertThat(response).isNotNull();

    assertThat(response.getStatusCode().value()).isEqualTo(200);

    assertThat(Objects.requireNonNull(response.getBody()).getCpf()).isEqualTo(cpf);
  }

  @Test
  @DisplayName("updatePatient return updated Patient DTO when successful")
  public void updatePatient_returnUpdatedPatientDTO_whenSuccessful() {

    NewPatientRequestDTO patientToBeUpdated =
        NewPatientRequestDTO.builder()
            .email("test2@test.com")
            .phone("(45)9995595959")
            .fullName("Dodói Teste da Silva")
            .cep("85858150")
            .mothersName("Mamai Do Dodói")
            .addressLine2("blablabla")
            .addressCountry("teste")
            .build();
    ResponseEntity<NewPatientRequestDTO> response =
        testRestTemplate.exchange(
            "/api/pacientes/{uuid}",
            HttpMethod.PUT,
            new HttpEntity<>(patientToBeUpdated),
            NewPatientRequestDTO.class,
            uuid);

    assertThat(response).isNotNull();
  }

  @Test
  @DisplayName("deletePatient returns String when successful")
  public void deletePatient_returnUpdatedPatientDTO_whenSuccessful() {

    ResponseEntity<String> response =
        testRestTemplate.exchange(
            "/api/pacientes/{uuid}", HttpMethod.DELETE, null, String.class, uuid);

    assertThat(response).isNotNull();
  }
}
