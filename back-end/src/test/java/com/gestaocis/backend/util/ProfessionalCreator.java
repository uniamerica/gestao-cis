package com.gestaocis.backend.util;

import com.gestaocis.backend.DTOs.ProfessionalDTO.ProfessionalRequestDTO;
import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.models.User;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class ProfessionalCreator {

    public static ProfessionalRequestDTO createValidProfessionalRequestDTOToBeSaved(){
        return ProfessionalRequestDTO
                .builder()
                .cpf("987987987987")
                .rg("98778998798")
                .professionalDocument("456987456")
                .email("professional@email.com")
                .phone("99877899")
                .birthdate(Instant.now())
                .sex('M')
                .addressCountry("Brasil")
                .addressLine2("Foz do iguacu")
                .fullName("Bruno Sousa Dias")
                .password("123456798")
                .specialties(List
                        .of("3e30a1f4-4c86-48d0-a118-1ed453379a76","b55ccdfa-e227-4a36-aa05-a7f88ff30bcb","4fe11b9d-4f33-41e5-82f6-b52636c9bfe9"))
                .build();
    }

    public static ProfessionalRequestDTO createValidProfessionalRequestDTOToBeUpdated(){
        return ProfessionalRequestDTO
                .builder()
                .cpf("987987987987")
                .rg("98778998798")
                .professionalDocument("456987456")
                .email("professional2@email.com")
                .phone("99877899")
                .birthdate(Instant.now())
                .sex('M')
                .addressCountry("Brasil")
                .addressLine2("Foz do iguacu")
                .fullName("Bruno Sousa Dias 2")
                .password("123456798")
                .specialties(List
                        .of("3e30a1f4-4c86-48d0-a118-1ed453379a76","b55ccdfa-e227-4a36-aa05-a7f88ff30bcb","4fe11b9d-4f33-41e5-82f6-b52636c9bfe9"))
                .build();
    }

    public static User createValidUserProfessional(){
        RoleEntity role = RoleEntity.builder()
                .roleName(Role.ROLE_PROFESSIONAL).build();

        Address address = Address.builder()
                .id(1L)
                .cep("85858150")
                .city("Foz do Iguaçu")
                .uf("PR")
                .neighborhood("Bairro de testes")
                .street("Rua de Testes")
                .build();

        return User.builder()
                .id(1L)
                .active(true)
                .uuid(UUID.randomUUID())
                .cpf("987987987987")
                .rg("98778998798")
                .professionalDocument("456987456")
                .email("professional2@email.com")
                .phone("99877899")
                .birthdate(Instant.now())
                .sex('M')
                .addressCountry("Brasil")
                .addressLine2("Foz do iguacu")
                .fullName("Bruno Sousa Dias 2")
                .address(address)
                .role(role)
                .password("123456798")
                .build();
    }
}
