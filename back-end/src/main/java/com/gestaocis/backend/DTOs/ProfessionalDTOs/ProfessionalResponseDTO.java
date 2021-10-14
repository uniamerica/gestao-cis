package com.gestaocis.backend.DTOs.ProfessionalDTOs;

import com.gestaocis.backend.DTOs.AddressDTO.AddressDTO;
import com.gestaocis.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfessionalResponseDTO {
    // User
    private UUID uuid;
    private String cpf;
    private String rg;
    private String email;
    private String phone;
    private String fullName;
    private String mothersName;
    private Instant birthdate;
    private Character sex;
    private String placeOfBirth;
    private String password;
    private String professionalDocument;

    private AddressDTO address;

    public ProfessionalResponseDTO(User professional) {
        this.uuid = professional.getUuid();
        this.cpf = professional.getCpf();
        this.rg = professional.getRg();
        this.email = professional.getEmail();
        this.phone = professional.getPhone();
        this.fullName = professional.getFullName();
        this.mothersName = professional.getMothersName();
        this.birthdate = professional.getBirthdate();
        this.sex = professional.getSex();
        this.placeOfBirth = professional.getPlaceOfBirth();
        this.professionalDocument = professional.getProfessionalDocument();
        this.address = AddressDTO.builder()
                .addressCountry(professional.getAddressCountry())
                .addressLine2(professional.getAddressLine2())
                .cep(professional.getAddress().getCep())
                .city(professional.getAddress().getCity())
                .neighborhood(professional.getAddress().getNeighborhood())
                .uf(professional.getAddress().getUf())
                .street(professional.getAddress().getStreet())
                .build();
    }

}
