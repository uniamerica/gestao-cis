package com.cis.utils;

import com.cis.model.Admin;
import com.cis.model.dto.AdminDTO.AdminCreationDTO;
import com.cis.model.dto.AdminDTO.AdminReturnDTO;
import com.cis.model.dto.AdminDTO.AdminUpdateDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public class AdminCreator {
    public static Admin createAdminToBeSaved(){
        Admin admin = new Admin();
        admin.setName("Teste Admin");
        admin.setRole("ROLE_ADMIN");
        admin.setPhone("(45) 999 155 581");
        admin.setActive(true);
        admin.setEmail("teste@admin.com");
        admin.setPassword(new BCryptPasswordEncoder().encode("senhaFortissima"));
        admin.setId(UUID.randomUUID());

        return admin;
    }

    public static Admin creatAdminSaved(){
        Admin admin = new Admin();
        admin.setName("Teste Admin");
        admin.setRole("ROLE_ADMIN");
        admin.setPhone("(45) 999 155 581");
        admin.setActive(true);
        admin.setEmail("teste@admin.com");
        admin.setPassword("senhaFortissima");
        admin.setId(UUID.randomUUID());

        return admin;
    }

    public static AdminCreationDTO createAdminDTOToBeSaved(){
        return AdminCreationDTO
                .builder()
                .email("teste@admin.com")
                .password("senhaFortissima")
                .name("Teste Admin")
                .phone("(45) 999 155 581")
                .build();
    }

    public static AdminUpdateDTO adminDTOToBeUpdated() {
        return AdminUpdateDTO
                .builder()
                .email("teste@admin.com")
                .name("Teste Admin")
                .phone("(45) 999 155 581")
                .build();
    }

    public static AdminReturnDTO createAdminDTOSaved(){
        return AdminReturnDTO
                .builder()
                .id(UUID.randomUUID())
                .name("Teste Admin")
                .email("teste@creator.com")
                .build();
    }
}
