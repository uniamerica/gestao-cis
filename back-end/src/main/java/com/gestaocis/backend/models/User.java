package com.gestaocis.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.enums.SpecialtyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Entity
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable, UserDetails {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String cpf;

  @Column(nullable = false)
  private String rg;

  @Column private String professionalDocument;

  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false)
  private String fullName;

  @Column(nullable = false)
  private Instant birthdate;

  private String mothersName;

  @Column(nullable = false)
  private Character sex;

  private String placeOfBirth;

  @Column(nullable = false)
  private String addressCountry;

  @Column(nullable = false)
  private String addressLine2;

  @Column(nullable = false)
  private String password;

  @Column(
      name = "active",
      nullable = false,
      insertable = false,
      columnDefinition = "boolean default true")
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private boolean active;

  @ManyToOne(fetch = FetchType.LAZY)
  private RoleEntity role;

  @JoinColumn(name = "addressId")
  @ManyToOne(fetch = FetchType.LAZY)
  private Address address;

  @ManyToOne(fetch = FetchType.LAZY)
  private HealthInsurance healthInsurance;

  @OneToMany(mappedBy = "patient")
  private List<Appointment> appointments = new ArrayList<>();

  @ManyToMany
  @JoinTable(
      name = "user_has_specialties",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "specialty_id"))
  private List<SpecialtyEntity> specialties = new ArrayList<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.getRoleName().toString()));
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.active;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.active;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.active;
  }

  @Override
  public boolean isEnabled() {
    return this.active;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("User: {");
    sb.append("id=").append(id);
    sb.append(", uuid=").append(uuid);
    sb.append(", role=").append(role);
    sb.append(", specialty=").append(specialties);
    sb.append(", professionalDocument").append(professionalDocument);
    sb.append(", phone=").append(phone);
    sb.append(", email=").append(email);
    sb.append(", cpf=").append(rg);
    sb.append(", fullName=").append(fullName);
    sb.append(", password=").append(password);
    sb.append("}");
    return sb.toString();
  }
}
