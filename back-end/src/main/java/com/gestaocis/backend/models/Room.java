package com.gestaocis.backend.models;

import com.gestaocis.backend.enums.SpecialtyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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

  @ManyToMany
  @JoinTable(
          name = "room_has_specialties",
          joinColumns = @JoinColumn(name = "room_id"),
          inverseJoinColumns = @JoinColumn(name = "specialty_id"))
  private List<SpecialtyEntity> specialties = new ArrayList<>();

  @OneToMany private List<Appointment> appointments = new ArrayList<>(); //compromisso
}
