package com.cis.integration;

import com.cis.model.HealthProfessional;
import com.cis.model.dto.HeathProfessionalDTO.HealthProfessionalCreationDTO;
import com.cis.model.dto.HeathProfessionalDTO.HealthProfessionalResponseDTO;
import com.cis.model.dto.UserDTO.AuthenticationRequest;
import com.cis.repository.HealthProfessionalRepository;
import com.cis.utils.HealthProfessionalCreator;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class HealthProfessionalIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private HealthProfessionalRepository healthProfessionalRepository;

    @BeforeEach
    void setup(){

    }

    @Test
    @DisplayName("Health Professional Integration Test - CREATE")
    public void test_create(){
        ResponseEntity<HealthProfessionalResponseDTO> create = testRestTemplate.exchange("/api/health-professionals", HttpMethod.POST, new HttpEntity<>(HealthProfessionalCreator.createHealthProfessionalDTOToBeSaved()), HealthProfessionalResponseDTO.class);

        Assertions.assertThat(create).isNotNull();
        Assertions.assertThat(create.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("Health Professional Integration Test - LOGIN")
    public void test_login(){
        HealthProfessional saved = healthProfessionalRepository.save(HealthProfessionalCreator.createHealthProfessionalToBeSaved());

        AuthenticationRequest auth = AuthenticationRequest
                .builder()
                .email("teste@teste.com")
                .password("teste123")
                .build();


        ResponseEntity<Object> login = testRestTemplate.exchange("/api/health-professionals/login", HttpMethod.POST, new HttpEntity<>(auth), Object.class);
        Assertions.assertThat(login).isNotNull();
        Assertions.assertThat(login.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    @DisplayName("Health Professional Integration Test - FIND BY ID")
    public void test_findById(){
        HealthProfessional saved = healthProfessionalRepository.save(HealthProfessionalCreator.createHealthProfessionalToBeSaved());

        ResponseEntity<HealthProfessionalResponseDTO> findById = testRestTemplate.exchange("/api/health-professionals/{id}", HttpMethod.GET, null, HealthProfessionalResponseDTO.class, saved.getId());
        Assertions.assertThat(findById).isNotNull();
        Assertions.assertThat(findById.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @DisplayName("Health Professional Integration Test - UPDATE")
    public void test_update(){
        HealthProfessional saved = healthProfessionalRepository.save(HealthProfessionalCreator.createHealthProfessionalToBeSaved());

        HealthProfessionalCreationDTO updateDto = HealthProfessionalCreator.createHealthProfessionalDTOToBeSaved();
        updateDto.setPhone("456789456");

        ResponseEntity<HealthProfessionalResponseDTO> update = testRestTemplate.exchange("/api/health-professionals/{id}", HttpMethod.PUT, new HttpEntity<>(updateDto), HealthProfessionalResponseDTO.class, saved.getId());
        Assertions.assertThat(update).isNotNull();
        Assertions.assertThat(update.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Health Professional Integration Test - DELETE")
    public void test_delete(){
        HealthProfessional saved = healthProfessionalRepository.save(HealthProfessionalCreator.createHealthProfessionalToBeSaved());

        ResponseEntity<String> delete = testRestTemplate.exchange("/api/health-professionals/{id}", HttpMethod.DELETE, null, String.class, saved.getId());
        Assertions.assertThat(delete).isNotNull();
        Assertions.assertThat(delete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}
