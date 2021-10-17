package com.gestaocis.backend.util;

import com.gestaocis.backend.DTOs.AddressDTO.AddressDTO;
import com.gestaocis.backend.DTOs.ProfessionalDTOs.ProfessionalResponseDTO;
import com.gestaocis.backend.DTOs.ProfessionalDTOs.ProfessionalResponseDTO;
import com.gestaocis.backend.DTOs.ProfessionalDTOs.NewProfessionalRequestDTO;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.RoleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.UUID;

public class ProfessionalCreator {
    
    public static NewProfessionalRequestDTO createValidProfessionalRequestDtoToBeSaved() {
        return NewProfessionalRequestDTO.builder()
                .cpf("11111111111")
                .rg("88888888888")
                .email("teste@deus_e_top.com")
                .phone("(45)99934-5678")
                .fullName("Teste de Profissional Sadio")
                .birthdate("1993-12-29")
                .sex('M')
                .addressCountry("Brasil")
                .addressLine2("Rua 123")
                .password("senhaSuperSuperSecreta")
                .cep("85853330")
                .street("Rua não asfaltada")
                .city("Foz do Iguaçu")
                .uf("PR")
                .neighborhood("Bairro Criativo")
                .build();
    }

    public static ProfessionalResponseDTO createValidProfessionalResponseDTOSaved(){
        return ProfessionalResponseDTO.builder()
                .uuid(UUID.randomUUID())
                .cpf("11111111111")
                .rg("88888888888")
                .email("teste@deus_e_top.com")
                .phone("(45)99934-5678")
                .fullName("Teste de Profissional Sadio")
                .birthdate(Instant.now())
                .sex('M')
                .address(AddressDTO.builder()
                        .addressCountry("Brasil")
                        .addressLine2("Rua 123")
                        .cep("85853330")
                        .street("Rua não asfaltada")
                        .city("Foz do Iguaçu")
                        .uf("PR")
                        .neighborhood("Bairro Criativo")
                        .build())
                .build();
    }

    public static ProfessionalResponseDTO createValidProfessionalResponseDTOUpdated(){
        return ProfessionalResponseDTO.builder()
                .uuid(UUID.randomUUID())
                .cpf("11111111111")
                .rg("88888888888")
                .email("test@test.com")
                .phone("(45)4002-8922")
                .fullName("Paulo Brificado")
                .birthdate(Instant.now())
                .sex('M')
                .address(AddressDTO.builder()
                        .addressCountry("Brazel")
                        .addressLine2("Rua da casa do meu vizinho")
                        .cep("85858150")
                        .street("Rua  nº 1567894")
                        .city("Foz do Iguaçu")
                        .uf("PR")
                        .neighborhood("Bairro da casa do meu vizinho")
                        .build())
                .build();
    }

    public static User createProfessionalToBeSaved(){
        return User.builder()
                .uuid(UUID.randomUUID())
                .active(true)
                .password("senha123")
                .sex('M')
                .addressCountry("Brazel")
                .addressLine2("Aquele endereço la")
                .birthdate(Instant.now())
                .rg("9999999")
                .cpf("99999999999")
                .phone("(45)98989898")
                .fullName("João Teste da Silva")
                .email("test@test.com")
                .professionalDocument("0000000")
                .build();


    }
}
