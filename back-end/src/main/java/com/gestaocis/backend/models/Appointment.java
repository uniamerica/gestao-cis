package com.gestaocis.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "appointments")
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;

  @ManyToOne
  @JoinColumn(name = "patientId", nullable = false)
  private User patient;

  @ManyToOne
  @JoinColumn(name = "professionalId", nullable = false)
  private User professional;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private LocalDateTime editedAt;

  @Column(nullable = false)
  private LocalDateTime scheduledFor;

  @ManyToOne
  @JoinColumn(name = "roomId", nullable = false)
  private Room room;

  @Lob
  @Column(columnDefinition = "TEXT",nullable = false)
  private String observation;

  @ManyToOne
  @JoinColumn(name = "creatorId", nullable = false)
  private User createdBy;

  @Column(nullable = false)
  private boolean isSupervised;

  @Column(nullable = false)
  private boolean isConfirmed;

  @Column(nullable = false)
  private boolean isPaid;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Appointment{");
    sb.append("id=").append(id);
    sb.append(", uuid=").append(uuid);
    sb.append(", patient=").append(patient);
    sb.append(", professional=").append(professional);
    sb.append(", createdAt=").append(createdAt);
    sb.append(", editedAt=").append(editedAt);
    sb.append(", scheduledFor=").append(scheduledFor);
    sb.append(", room=").append(room);
    sb.append(", observation='").append(observation).append('\'');
    sb.append(", createdBy=").append(createdBy);
    sb.append(", isSupervised=").append(isSupervised);
    sb.append(", isConfirmed=").append(isConfirmed);
    sb.append(", isPaid=").append(isPaid);
    sb.append('}');
    return sb.toString();
  }
}
