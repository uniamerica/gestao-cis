
package com.gestaocis.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "specialties")
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "specialty")
    private Set<User> especialists;

    @Lob
    @Column(columnDefinition = "TEXT",nullable = false)
    private String description;

    @ManyToMany(mappedBy = "specialties")
    private Set<Room> room;

}



