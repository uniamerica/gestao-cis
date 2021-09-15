package com.gestaocis.backend.utils.enums;

import javax.persistence.*;

@Entity
public class Specialty {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private com.gestaocis.backend.utils.enums.Specialty name;

  public Specialty() {}

  public Specialty(com.gestaocis.backend.utils.enums.Specialty name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public com.gestaocis.backend.utils.enums.Specialty getName() {
    return name;
  }

  public void setName(com.gestaocis.backend.utils.enums.Specialty name) {
    this.name = name;
  }
}
