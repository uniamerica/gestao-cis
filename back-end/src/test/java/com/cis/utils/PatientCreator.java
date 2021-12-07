package com.cis.utils;

import com.cis.model.Patient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class PatientCreator {
    public static Patient createPatientSaved() throws ParseException {
        Patient patient = new Patient();
        patient.setId(UUID.randomUUID());
        patient.setName("Marcelo Fortes");
        patient.setEmail("marcelo@mail.com");
        patient.setRg("000000000");
        patient.setCpf("111111111");
        patient.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1999-08-21"));
        patient.setPhone("(45)99999-9999");
        patient.setPassword(new BCryptPasswordEncoder().encode("salve"));
        patient.setMotherName("Luciana");
        patient.setGender('M');

        return patient;
    }
}
