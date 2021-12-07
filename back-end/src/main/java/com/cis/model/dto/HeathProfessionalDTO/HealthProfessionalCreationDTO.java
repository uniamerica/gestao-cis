package com.cis.model.dto.HeathProfessionalDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HealthProfessionalCreationDTO {

    private String email;
    private String password;
    private String name;
    private String phone;
    private String professionalDocument;
}
