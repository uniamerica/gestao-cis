package com.gestaocis.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
// @RequiredArgsConstructor
@Builder
@Table(name = "appointments")
public class Appointment implements Serializable {
  private static final long serialVersionUID = 1L;

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

  @CreationTimestamp
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private LocalDateTime createdAt;

  @Column(
      name = "editedAt",
      nullable = false,
      insertable = false,
      columnDefinition = "boolean default false")
  private LocalDateTime editedAt;

  @Column(nullable = false)
  private LocalDateTime scheduledFor;

  @ManyToOne
  @JoinColumn(name = "roomId", nullable = false)
  private Room room;

  @Lob
  @Column(columnDefinition = "TEXT", nullable = false)
  private String observation;

  @ManyToOne
  @JoinColumn(name = "creatorId", nullable = false)
  private User createdBy;

  @Column(name = "supervised", nullable = false, insertable = false)
  private boolean supervised;

  @Column(
      name = "confirmed",
      nullable = false,
      insertable = false,
      columnDefinition = "boolean default false")
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private boolean confirmed;

  @Column(name = "paid", nullable = false, insertable = false)
  private boolean paid;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Appointment that = (Appointment) o;

    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return 558524322;
  }
}
