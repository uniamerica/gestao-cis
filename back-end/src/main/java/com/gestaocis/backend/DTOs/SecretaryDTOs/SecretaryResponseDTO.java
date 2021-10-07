package com.gestaocis.backend.DTOs.SecretaryDTOs;

import com.gestaocis.backend.DTOs.AddressDTO.AddressDTO;
import com.gestaocis.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecretaryResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // User
    private UUID uuid;
    private String cpf;
    private String rg;
    private String email;
    private String phone;
    private String fullName;
    private Instant birthdate;
    private Character sex;

    private AddressDTO address;

    public SecretaryResponseDTO(User secretary){
        this.uuid = secretary.getUuid();
        this.cpf = secretary.getCpf();
        this.rg = secretary.getRg();
        this.email = secretary.getEmail();
        this.phone = secretary.getPhone();
        this.fullName = secretary.getFullName();
        this.birthdate = secretary.getBirthdate();
        this.sex= secretary.getSex();
        this.address = AddressDTO.builder()
                .addressCountry(secretary.getAddressCountry())
                .addressLine2(secretary.getAddressLine2())
                .cep(secretary.getAddress().getCep())
                .city(secretary.getAddress().getCity())
                .neighborhood(secretary.getAddress().getNeighborhood())
                .uf(secretary.getAddress().getUf())
                .street(secretary.getAddress().getStreet())
                .build();
    }


}
