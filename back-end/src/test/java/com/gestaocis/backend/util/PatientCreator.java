package com.gestaocis.backend.util;

import com.gestaocis.backend.DTOs.AddressDTO.AddressDTO;
import com.gestaocis.backend.DTOs.PatientDTOs.NewPatientRequestDTO;
import com.gestaocis.backend.DTOs.PatientDTOs.PatientResponseDTO;
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
                .email("teste@amem.com")
                .phone("(45)999666999")
                .fullName("Teste de Paciente Dodói")
                .birthdate(Instant.now())
                .sex('F')
                .address(AddressDTO.builder()
                        .addressCountry("Brasil")
                        .addressLine2("Rua 3")
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
                .email("test@test.com")
                .phone("(45)9995595959")
                .fullName("João Teste da Silva")
                .birthdate(Instant.now())
                .sex('M')
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
}
