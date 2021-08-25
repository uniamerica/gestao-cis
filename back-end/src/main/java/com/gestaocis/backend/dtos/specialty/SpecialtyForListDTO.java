package com.gestaocis.backend.dtos.specialty;

import com.gestaocis.backend.models.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyForListDTO {
    private String title;

    public SpecialtyForListDTO(Specialty specialty){
        title = specialty.getTitle();
    }
}
