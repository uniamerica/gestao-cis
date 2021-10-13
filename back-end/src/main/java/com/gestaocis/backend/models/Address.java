package com.gestaocis.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "O CEP deve ser preenchido")
  private String cep;

  @Column(unique = true)
  @SerializedName(value = "logradouro")
  private String street;

  @NotEmpty(message = "A cidade deve ser preenchido")
  @SerializedName(value = "localidade")
  private String city;

  //  @Column(nullable = false)
  private String uf;

  @NotEmpty(message = "O bairro deve ser preenchido")
  @SerializedName(value = "bairro")
  private String neighborhood;

  @JsonIgnore
  @OneToMany(mappedBy = "address")
  @ToString.Exclude
  private List<User> users = new ArrayList<>();

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