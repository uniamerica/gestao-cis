package com.gestaocis.backend.integration;

import com.gestaocis.backend.BackEndApplication;
import com.gestaocis.backend.DTOs.SecretaryDTOs.NewSecretaryRequestDTO;
import com.gestaocis.backend.DTOs.SecretaryDTOs.SecretaryResponseDTO;
import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.AddressRepository;
import com.gestaocis.backend.repositories.RoleEntityRepository;
import com.gestaocis.backend.repositories.UserRepository;
import com.gestaocis.backend.util.SecretaryCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.UUID;


@SpringBootTest(
        classes = BackEndApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class SecretaryIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RoleEntityRepository roleEntityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;


    @BeforeEach
    void setUp() throws Exception {
        // roleEntityRepository.save(RoleEntity.builder().roleName(Role.ROLE_SECRETARY).build());

        Address address = addressRepository.save(Address.builder()
                .cep("85858150")
                .street("rua tal")
                .city("cidade tal")
                .uf("parana")
                .neighborhood("bairro tal")
                .build());

        User userToBeSaved = SecretaryCreator.createSecretaryToBeSaved();
        userToBeSaved.setAddress(address);
        userRepository.save(userToBeSaved);
    }

    @Test
    @DisplayName("save Returns Saved Secretary DTO when successful")
    public void save_returnsSavedSecretaryDto_whenSuccessful(){

        NewSecretaryRequestDTO secretaryToBeCreatedDTO = SecretaryCreator.createValidSecretaryRequestDtoToBeSaved();

        ResponseEntity<SecretaryResponseDTO> response = testRestTemplate
                .postForEntity("/api/admin/secretaries", secretaryToBeCreatedDTO, SecretaryResponseDTO.class);

        Assertions.assertThat(response).isNotNull();

        Assertions.assertThat(response.getStatusCode().value()).isEqualTo(201);
        Assertions.assertThat(Objects.requireNonNull(response.getBody()).getEmail()).isEqualTo(secretaryToBeCreatedDTO.getEmail());
    }

    @Test
    @DisplayName("findByUuid returns SecretaryDTO when successful")
    public void findByUuid_returnsSecretaryDTO_whenSuccessful(){

        Address address = addressRepository.save(Address.builder()
                .cep("85858150")
                .street("rua tal")
                .city("cidade tal")
                .uf("parana")
                .neighborhood("bairro tal")
                .build());

        User userToBeSaved = SecretaryCreator.createSecretaryToBeSaved();
        userToBeSaved.setAddress(address);

        User userSaved = userRepository.save(userToBeSaved);
        ResponseEntity<SecretaryResponseDTO> response = testRestTemplate.getForEntity("/api/admin/secretaries/id/" + userSaved.getUuid(), SecretaryResponseDTO.class);

        Assertions.assertThat(response).isNotNull();

        Assertions.assertThat(response.getStatusCode().value()).isEqualTo(200);

        Assertions.assertThat(Objects.requireNonNull(response.getBody()).getUuid()).isEqualTo(userSaved.getUuid());
    }

    @Test
    @DisplayName("findByEmail returns SecretaryDTO when successful")
    public void findByEmail_returnsSecretaryDTO_whenSuccessful(){
        String email = SecretaryCreator.createValidSecretaryResponseDTOSaved().getEmail();

        ResponseEntity<SecretaryResponseDTO> response = testRestTemplate.getForEntity("/api/admin/secretaries/email/" + email, SecretaryResponseDTO.class);

        Assertions.assertThat(response).isNotNull();

        Assertions.assertThat(response.getStatusCode().value()).isEqualTo(200);

        Assertions.assertThat(Objects.requireNonNull(response.getBody()).getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("findByName returns list of SecretaryDTO when successful")
    public void findByName_returnsListOfSecretaryDTO_whenSuccessful(){
        String email = SecretaryCreator.createValidSecretaryResponseDTOSaved().getEmail();

        String name = SecretaryCreator.createValidSecretaryResponseDTOSaved().getFullName();

        ResponseEntity<SecretaryResponseDTO[]> response = testRestTemplate.getForEntity("/api/admin/secretaries/name/" + name,SecretaryResponseDTO[].class);

        Assertions.assertThat(response).isNotNull();

        Assertions.assertThat(response.getStatusCode().value()).isEqualTo(200);

    }

    @Test
    @DisplayName("findByRole Returns List of Secretary DTO when successful")
    public void findByRole_returnListOfSecretaryDTO_WhenSuccessful() {
        ResponseEntity<SecretaryResponseDTO[]> response = testRestTemplate.getForEntity("/api/admin/secretaries/role",SecretaryResponseDTO[].class);

        Assertions.assertThat(response)
                .isNotNull();

        Assertions.assertThat(response.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    @DisplayName("findByCpf returns SecretaryDTO when successful")
    public void findByCpf_returnsSecretaryDTO_whenSuccessful(){
        String cpf = SecretaryCreator.createValidSecretaryResponseDTOSaved().getCpf();

        ResponseEntity<SecretaryResponseDTO> response = testRestTemplate.getForEntity("/api/admin/secretaries/cpf/" + cpf, SecretaryResponseDTO.class);

        Assertions.assertThat(response).isNotNull();

        Assertions.assertThat(response.getStatusCode().value()).isEqualTo(200);

        Assertions.assertThat(Objects.requireNonNull(response.getBody()).getEmail()).isEqualTo(cpf);
    }




}
