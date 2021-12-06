package com.cis.utils;

import com.cis.model.HealthProfessional;
import com.cis.model.dto.HeathProfessionalDTO.HealthProfessionalCreationDTO;
import com.cis.model.dto.HeathProfessionalDTO.HealthProfessionalResponseDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public class HealthProfessionalCreator {

    public static HealthProfessional createHealthProfessionalToBeSaved(){
        HealthProfessional healthProfessional = new HealthProfessional();
        healthProfessional.setEmail("teste@teste.com");
        healthProfessional.setPassword(new BCryptPasswordEncoder().encode("teste123"));
        healthProfessional.setRole("ROLE_PROFESSIONAL");
        healthProfessional.setActive(true);
        healthProfessional.setProfessional_id(UUID.randomUUID());
        healthProfessional.setName("Professional Teste");
        healthProfessional.setPhone("99878997");
        healthProfessional.setProfessionalDocument("789987789987");

        return healthProfessional;
    }

    public static HealthProfessional createHealthProfessionalSaved(){
        HealthProfessional healthProfessional = new HealthProfessional();
        healthProfessional.setId(UUID.randomUUID());
        healthProfessional.setEmail("teste@teste.com");
        healthProfessional.setPassword(new BCryptPasswordEncoder().encode("teste123"));
        healthProfessional.setRole("ROLE_PROFESSIONAL");
        healthProfessional.setActive(true);
        healthProfessional.setProfessional_id(UUID.randomUUID());
        healthProfessional.setName("Professional Teste");
        healthProfessional.setPhone("99878997");
        healthProfessional.setProfessionalDocument("789987789987");
        return healthProfessional;
    }

    public static HealthProfessionalCreationDTO createHealthProfessionalDTOToBeSaved(){
        return HealthProfessionalCreationDTO
                .builder()
                .email("teste@teste.com")
                .password("teste123")
                .name("Professional Teste")
                .phone("99878997")
                .professionalDocument("789987789987")
                .build();
    }

    public static HealthProfessionalResponseDTO createHealthProfessionalDTOSaved(){
        return HealthProfessionalResponseDTO
                .builder()
                .id(UUID.randomUUID())
                .professional_id(UUID.randomUUID())
                .email("teste@teste.com")
                .name("Professional Teste")
                .phone("99878997")
                .professionalDocument("789987789987")
                .build();
    }
}
