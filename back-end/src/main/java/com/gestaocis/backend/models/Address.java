package com.gestaocis.backend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Address implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String cep;

  @Column(nullable = false)
  private String street;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String uf;

  @Column(nullable = false)
  private String neighborhood;

  @OneToMany private List<User> users = new ArrayList<>();

  public Address() {}

  public Address(Long id, String cep, String street, String city, String uf, String neighborhood) {
    this.id = id;
    this.cep = cep;
    this.street = street;
    this.city = city;
    this.uf = uf;
    this.neighborhood = neighborhood;
  }

  public Long getId() {
    return id;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }
}