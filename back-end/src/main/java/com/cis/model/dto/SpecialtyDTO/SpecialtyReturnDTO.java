package com.cis.model.dto.SpecialtyDTO;

import com.cis.model.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyReturnDTO {

  private UUID id;
  private String name;

  public SpecialtyReturnDTO(Specialty specialty) {
    this.id = specialty.getId();
    this.name = specialty.getName();
  }
}
