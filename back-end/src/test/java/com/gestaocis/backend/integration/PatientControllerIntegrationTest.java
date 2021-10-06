package com.gestaocis.backend.integration;

import com.gestaocis.backend.BackEndApplication;
import com.gestaocis.backend.DTOs.PatientDTOs.NewPatientRequestDTO;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.AddressRepository;
import com.gestaocis.backend.util.AddressCreator;
import com.gestaocis.backend.util.PatientCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = BackEndApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired private TestRestTemplate restTemplate;

    @Autowired
    private AddressRepository repository;

    @BeforeEach
    void setUp() throws Exception {
        repository.saveAll(AddressCreator.createAddressList());
    }

    @Test
    public void shouldSavePatient() {
        NewPatientRequestDTO patient = PatientCreator.createValidPatientRequestDtoToBeSaved();
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:"+ port +"/api/pacientes", patient, String.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }
}
