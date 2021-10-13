package com.gestaocis.backend.DTOs.AddressDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddressDTO {

    private String addressCountry;
    private String addressLine2;
    private String cep;
    private String street;
    private String city;
    private String uf;
    private String neighborhood;


}
