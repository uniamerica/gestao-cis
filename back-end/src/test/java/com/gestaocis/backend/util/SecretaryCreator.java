package com.gestaocis.backend.util;

import com.gestaocis.backend.DTOs.AddressDTO.AddressDTO;
import com.gestaocis.backend.DTOs.SecretaryDTOs.NewSecretaryRequestDTO;
import com.gestaocis.backend.DTOs.SecretaryDTOs.SecretaryResponseDTO;
import com.gestaocis.backend.repositories.RoleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.UUID;

public class SecretaryCreator {

    @Autowired
    private RoleEntityRepository roleEntityRepository;


    public static NewSecretaryRequestDTO createValidSecretaryRequestDtoToBeSaved(){
        return NewSecretaryRequestDTO.builder()
                .cpf("99999999999")
                .rg("88888888888")
                .email("test@test.com")
                .phone("(45)9995595959")
                .fullName("João Teste da Silva")
                .birthdate("1997-10-30")
                .sex('M')
                .addressCountry("Brazel")
                .addressLine2("Rua da minha casa")
                .password("senha123")
                .cep("85858150")
                .street("Rua teste")
                .city("Foz do Iguaçu")
                .uf("PR")
                .neighborhood("Bairro da minha casa")
                .build();
    }

    public static SecretaryResponseDTO createValidSecretaryResponseDTOSaved(){
        return SecretaryResponseDTO.builder()
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

    public static SecretaryResponseDTO createValidSecretaryResponseDTOUpdated(){
        return SecretaryResponseDTO.builder()
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
