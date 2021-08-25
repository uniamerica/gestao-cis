package com.gestaocis.backend.dtos.user;

import com.gestaocis.backend.dtos.specialty.SpecialtyForListDTO;
import com.gestaocis.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListDTO {
    private UUID uuid;
    private SpecialtyForListDTO specialty;
    private String fullName;
    private String email;

    public UserListDTO(User user){
        uuid = user.getUuid();
        specialty = new SpecialtyForListDTO(user.getSpecialty());
        fullName = user.getFullName();
        email = user.getEmail();
    }
}
