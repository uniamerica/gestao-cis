package com.gestaocis.backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ProfessionalRepositoryTest {
    @Autowired
    private PatientRepository patientRepository;
}