package com.gestaocis.backend.util;

import com.gestaocis.backend.DTOs.AddressDTO.AddressDTO;
import com.gestaocis.backend.DTOs.PatientDTOs.NewPatientRequestDTO;
import com.gestaocis.backend.DTOs.PatientDTOs.PatientResponseDTO;
import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.RoleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.UUID;

public class PatientCreator {

    @Autowired
    private RoleEntityRepository roleEntityRepository;


    public static NewPatientRequestDTO createValidPatientRequestDtoToBeSaved(){
        return NewPatientRequestDTO.builder()
                .cpf("99999999999")
                .rg("66666666666")
                .email("teste@amem.com")
                .phone("(45)999666999")
                .fullName("Teste de Paciente Dodói")
                .birthdate("1993-12-29")
                .sex('F')
                .addressCountry("Brasil")
                .addressLine2("Rua 2")
                .password("senhaSegura")
                .cep("85853330")
                .street("Rua esburacada")
                .city("Foz do Iguaçu")
                .uf("PR")
                .neighborhood("Bairro abandonado")
                .build();
    }

    public static PatientResponseDTO createValidPatientResponseDTOSaved(){
        return PatientResponseDTO.builder()
                .uuid(UUID.randomUUID())
                .cpf("99999999999")
                .rg("66666666666")
                .email("integration@test.com.br")
                .phone("(45)999666999")
                .fullName("Teste de Paciente Dodói")
                .birthdate(Instant.now())
                .mothersName("Mamai Saudável")
                .sex('F')
                .address(AddressDTO.builder()
                        .addressCountry("Brasil")
                        .addressLine2("Casa")
                        .cep("85853330")
                        .street("Rua esburacada")
                        .city("Foz do Iguaçu")
                        .uf("PR")
                        .neighborhood("Bairro abandonado")
                        .build())
                .build();
    }

    public static PatientResponseDTO createValidPatientResponseDTOUpdated(){
        return PatientResponseDTO.builder()
                .uuid(UUID.randomUUID())
                .cpf("99999999999")
                .rg("88888888888")
                .email("integration@test.com.br")
                .phone("(45)9995595959")
                .fullName("Paciente Dodói da Silva")
                .birthdate(Instant.now())
                .sex('F')
                .address(AddressDTO.builder()
                        .addressCountry("Brazel")
                        .addressLine2("Rua da minha casa")
                        .cep("85858150")
                        .street("Rua teste")
                        .city("Foz do Iguaçu")
                        .uf("PR")
                        .neighborhood("Bairro da minha casa")
                        .build())
                .build();
    }

    public static User createValidUserPatient(){
        RoleEntity role = RoleEntity.builder()
                .roleName(Role.ROLE_PATIENT)
                .build();

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
                .uuid(UUID.randomUUID())
                .role(role)
                .address(address)
                .active(true)
                .password("senha123")
                .sex('F')
                .addressCountry("Brazel")
                .addressLine2("Aquele endereço la")
                .birthdate(Instant.now())
                .placeOfBirth("Argélia")
                .mothersName("Mamai dodói")
                .rg("9999999")
                .cpf("99999999999")
                .phone("(45)98989898")
                .fullName("Paciente Dodói da Silva")
                .email("test@test.com")
                .build();
    }

    public static User createPatientToBeSaved(){
        return User.builder()
                .uuid(UUID.randomUUID())
                .active(true)
                .password("senha123")
                .sex('M')
                .addressCountry("Brazel")
                .addressLine2("Aquele endereço la")
                .birthdate(Instant.now())
                .rg("9999999")
                .cpf("99966699900")
                .phone("(45)98989898")
                .fullName("Paciente Dodói da Silva")
                .email("integration@test.com.br")
                .build();
    }
}
