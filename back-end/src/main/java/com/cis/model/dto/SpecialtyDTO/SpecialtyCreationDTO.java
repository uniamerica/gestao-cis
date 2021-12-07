package com.cis.model.dto.SpecialtyDTO;

import com.cis.model.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecialtyCreationDTO {

  private String name;

  public SpecialtyCreationDTO(Specialty specialty) {
    this.name = specialty.getName();
  }
}
