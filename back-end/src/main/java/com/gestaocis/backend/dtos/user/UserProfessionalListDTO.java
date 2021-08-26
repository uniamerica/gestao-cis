package com.gestaocis.backend.dtos.user;

import com.gestaocis.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfessionalListDTO {
    private UUID uuid;
    private String fullName;
    private String email;
    private String specialty;

    public UserProfessionalListDTO(User user){
        uuid = user.getUuid();
        fullName = user.getFullName();
        email = user.getEmail();
        specialty = user.getSpecialty().getSpecialtyValue();
    }
}
