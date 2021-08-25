package com.gestaocis.backend.dtos.user;

import com.gestaocis.backend.dtos.specialty.SpecialtyDTO;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.models.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfessionalDTO {

    private UUID uuid;
    private String email;
    private String professionalDocument;
    private String cpf;
    private String rg;
    private String fullName;
    private String phone;
    private SpecialtyDTO specialty;

    public UserProfessionalDTO(User user){
        uuid = user.getUuid();
        email = user.getEmail();
        professionalDocument = user.getProfessionalDocument();
        cpf = user.getCpf();
        rg = user.getRg();
        fullName = user.getFullName();
        phone = user.getFullName();
        specialty = new SpecialtyDTO(user.getSpecialty());
    }

}
