package com.gestaocis.backend.util;

import com.gestaocis.backend.DTOs.AddressDTO.AddressDTO;
import com.gestaocis.backend.DTOs.SecretaryDTOs.NewSecretaryRequestDTO;
import com.gestaocis.backend.DTOs.SecretaryDTOs.SecretaryResponseDTO;
import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.models.User;

import java.time.Instant;
import java.util.UUID;

public class SecretaryCreator {

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

    public static User createValidUserSecretary(){
        RoleEntity role = RoleEntity.builder()
                .roleName(Role.ROLE_SECRETARY)
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
                .sex('M')
                .addressCountry("Brazel")
                .addressLine2("Aquele endereço la")
                .birthdate(Instant.now())
                .rg("9999999")
                .cpf("99999999999")
                .phone("(45)98989898")
                .fullName("João Teste da Silva")
                .email("test@test.com")
                .build();
    }

    public static  User createSecretaryToBeSaved(){
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
                .build();
    }

}
