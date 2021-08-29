package com.gestaocis.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "rooms")
public class Room {

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
            name = "specialties_room",
            joinColumns = @JoinColumn(name = "roomId"),
            inverseJoinColumns = @JoinColumn(name = "specialtyId")
    )
    private Set<Specialty> specialties;

    @OneToMany
    private List<Appointment> appointments;
}
