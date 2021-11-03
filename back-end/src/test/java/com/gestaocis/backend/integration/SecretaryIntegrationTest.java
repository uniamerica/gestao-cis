package com.gestaocis.backend.integration;

import com.gestaocis.backend.DTOs.SecretaryDTOs.NewSecretaryRequestDTO;
import com.gestaocis.backend.DTOs.SecretaryDTOs.SecretaryResponseDTO;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.AddressRepository;
import com.gestaocis.backend.repositories.RoleEntityRepository;
import com.gestaocis.backend.repositories.UserRepository;
import com.gestaocis.backend.util.SecretaryCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Log4j2
public class SecretaryIntegrationTest {

  @Autowired private TestRestTemplate testRestTemplate;

  @Autowired private RoleEntityRepository roleEntityRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private AddressRepository addressRepository;

  private UUID uuid;

  @BeforeEach
  void setUp() throws Exception {
    // roleEntityRepository.save(RoleEntity.builder().roleName(Role.ROLE_SECRETARY).build());
    Address address =
        addressRepository.save(
            Address.builder()
                .cep("85858150")
                .street("rua tal")
                .city("cidade tal")
                .uf("parana")
                .neighborhood("bairro tal")
                .build());

    User userToBeSaved = SecretaryCreator.createSecretaryToBeSaved();
    userToBeSaved.setAddress(address);
    userRepository.save(userToBeSaved);

    uuid = userToBeSaved.getUuid();
  }

  @Test
  @DisplayName("save Returns Saved Secretary DTO when successful")
  public void save_returnsSavedSecretaryDto_whenSuccessful() {

    NewSecretaryRequestDTO secretaryToBeCreatedDTO =
        NewSecretaryRequestDTO.builder()
            .cpf("99999999991")
            .rg("88888888881")
            .email("test2@test.com")
            .phone("(45)9995595959")
            .fullName("João Teste da Silva")
            .birthdate("1997-10-30")
            .sex('M')
            .addressCountry("Brazel")
            .addressLine2("Rua da minha casa")
            .password("senha123")
            .cep("85858150")
            .street("Rua teste")
            .city("Foz do Iguaçu")
            .uf("PR")
            .neighborhood("Bairro da minha casa")
            .build();

    ResponseEntity<SecretaryResponseDTO> response =
        testRestTemplate.postForEntity(
            "/api/admin/secretaries", secretaryToBeCreatedDTO, SecretaryResponseDTO.class);

    Assertions.assertThat(response).isNotNull();

    Assertions.assertThat(response.getStatusCode().value()).isEqualTo(201);
    Assertions.assertThat(Objects.requireNonNull(response.getBody()).getEmail())
        .isEqualTo(secretaryToBeCreatedDTO.getEmail());
  }

  @Test
  @DisplayName("findByUuid returns SecretaryDTO when successful")
  public void findByUuid_returnsSecretaryDTO_whenSuccessful() {
    ResponseEntity<SecretaryResponseDTO> response =
        testRestTemplate.exchange(
            "/api/admin/secretaries/id/{uuid}",
            HttpMethod.GET,
            null,
            SecretaryResponseDTO.class,
            uuid);

    Assertions.assertThat(response).isNotNull();

    Assertions.assertThat(response.getStatusCode().value()).isEqualTo(200);

    Assertions.assertThat(Objects.requireNonNull(response.getBody()).getUuid()).isEqualTo(uuid);
  }

  @Test
  @DisplayName("findByEmail returns SecretaryDTO when successful")
  public void findByEmail_returnsSecretaryDTO_whenSuccessful() {
    String email = SecretaryCreator.createValidSecretaryResponseDTOSaved().getEmail();

    ResponseEntity<SecretaryResponseDTO> response =
        testRestTemplate.getForEntity(
            "/api/admin/secretaries/email/" + email, SecretaryResponseDTO.class);

    Assertions.assertThat(response).isNotNull();

    Assertions.assertThat(response.getStatusCode().value()).isEqualTo(200);

    Assertions.assertThat(Objects.requireNonNull(response.getBody()).getEmail()).isEqualTo(email);
  }

  @Test
  @DisplayName("findByName returns list of SecretaryDTO when successful")
  public void findByName_returnsListOfSecretaryDTO_whenSuccessful() {
    String email = SecretaryCreator.createValidSecretaryResponseDTOSaved().getEmail();

    String name = SecretaryCreator.createValidSecretaryResponseDTOSaved().getFullName();

    ResponseEntity<SecretaryResponseDTO[]> response =
        testRestTemplate.getForEntity(
            "/api/admin/secretaries/name/" + name, SecretaryResponseDTO[].class);

    Assertions.assertThat(response).isNotNull();

    Assertions.assertThat(response.getStatusCode().value()).isEqualTo(200);
  }

  @Test
  @DisplayName("findByRole Returns List of Secretary DTO when successful")
  public void findByRole_returnListOfSecretaryDTO_WhenSuccessful() {
    ResponseEntity<SecretaryResponseDTO[]> response =
        testRestTemplate.getForEntity("/api/admin/secretaries/role", SecretaryResponseDTO[].class);

    Assertions.assertThat(response).isNotNull();

    Assertions.assertThat(response.getStatusCode().value()).isEqualTo(200);
  }

  @Test
  @DisplayName("findByCpf returns SecretaryDTO when successful")
  public void findByCpf_returnsSecretaryDTO_whenSuccessful() {
    String cpf = SecretaryCreator.createSecretaryToBeSaved().getCpf();

    ResponseEntity<SecretaryResponseDTO> response =
        testRestTemplate.getForEntity(
            "/api/admin/secretaries/cpf/" + cpf, SecretaryResponseDTO.class);

    Assertions.assertThat(response).isNotNull();

    Assertions.assertThat(response.getStatusCode().value()).isEqualTo(200);

    Assertions.assertThat(Objects.requireNonNull(response.getBody()).getCpf()).isEqualTo(cpf);
  }

  @Test
  @DisplayName("updateSecretary return updated SecretaryDTO when successful")
  public void updateSecretary_returnUpdatedSecretaryDTO_whenSuccessful() {

    NewSecretaryRequestDTO secretaryToBeUpdated =
        NewSecretaryRequestDTO.builder()
            .email("test2@test.com")
            .phone("(45)9995595959")
            .fullName("João Teste da Silva")
            .cep("85858150")
            .addressLine2("blablabla")
            .addressCountry("teste")
            .build();
    ResponseEntity<NewSecretaryRequestDTO> response =
        testRestTemplate.exchange(
            "/api/admin/secretaries/{uuid}",
            HttpMethod.PUT,
            new HttpEntity<>(secretaryToBeUpdated),
            NewSecretaryRequestDTO.class,
            uuid);

    Assertions.assertThat(response).isNotNull();
  }

  @Test
  @DisplayName("deleteSecretary return String when successful")
  public void deleteSecretary_returnUpdatedSecretaryDTO_whenSuccessful() {

    ResponseEntity<String> response =
        testRestTemplate.exchange(
            "/api/admin/secretaries/{uuid}", HttpMethod.DELETE, null, String.class, uuid);

    Assertions.assertThat(response).isNotNull();
  }
}
