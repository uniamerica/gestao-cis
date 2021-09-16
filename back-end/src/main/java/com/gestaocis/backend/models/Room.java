package com.gestaocis.backend.models;

import com.gestaocis.backend.utils.enums.SpecialtyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "rooms")
public class Room implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;

  @Column(unique = true, nullable = false)
  private Integer roomNumber;

  @OneToMany private Set<SpecialtyEntity> specialties = new HashSet<>();

  @OneToMany private List<Appointment> appointments;
}
