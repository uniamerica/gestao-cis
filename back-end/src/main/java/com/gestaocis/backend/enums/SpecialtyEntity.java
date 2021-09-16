package com.gestaocis.backend.enums;

import com.gestaocis.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "specialties")
public class SpecialtyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;

  private String specialtyName;

  @ManyToMany(mappedBy = "specialties", cascade = CascadeType.ALL)
  private List<User> users = new ArrayList<>();
}
