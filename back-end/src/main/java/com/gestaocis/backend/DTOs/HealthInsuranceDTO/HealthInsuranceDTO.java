package com.gestaocis.backend.DTOs.HealthInsuranceDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HealthInsuranceDTO {
    private String insuranceName;
    private String registrationNumber;
}
