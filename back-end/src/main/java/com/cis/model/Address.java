package com.cis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Address implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @NotEmpty(message = "O CEP deve ser preenchido")
  private String cep;

  @Column(unique = true)
  @SerializedName(value = "logradouro")
  private String street;

  @NotEmpty(message = "A cidade deve ser preenchida")
  @SerializedName(value = "localidade")
  private String city;

  //  @Column(nullable = false)
  private String uf;

  @NotEmpty(message = "O bairro deve ser preenchido")
  @SerializedName(value = "bairro")
  private String neighborhood;

  @JsonIgnore
  @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
  @ToString.Exclude
  private List<Patient> users = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Address address = (Address) o;

    return Objects.equals(id, address.id);
  }

  @Override
  public int hashCode() {
    return 1395783287;
  }
}
