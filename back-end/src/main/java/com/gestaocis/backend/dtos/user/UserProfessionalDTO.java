package com.gestaocis.backend.dtos.user;

import com.gestaocis.backend.models.User;
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
    private String specialty;

    public UserProfessionalDTO(User user){
        uuid = user.getUuid();
        email = user.getEmail();
        professionalDocument = user.getProfessionalDocument();
        cpf = user.getCpf();
        rg = user.getRg();
        fullName = user.getFullName();
        phone = user.getFullName();
    }

}
