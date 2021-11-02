package com.gestaocis.backend.repositories;

import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.models.HealthInsurance;
import com.gestaocis.backend.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("PatientRepository tests")
class PatientRepositoryTest {

  @Autowired private UserRepository repository;

  @Autowired private RoleEntityRepository roleRepository;

  @Autowired private SpecialtyEntityRepository specialtyEntityRepository;

  @Autowired private HealthInsuranceRepository healthInsuranceRepository;

  private User createUser() {
    RoleEntity role = roleRepository.save(RoleEntity.builder().roleName(Role.ROLE_PATIENT).build());

    HealthInsurance healthInsurance =
        healthInsuranceRepository.save(
            HealthInsurance.builder()
                .insuranceName("Concordian")
                .registrationNumber("789456")
                .build());

    return User.builder()
        .uuid(UUID.randomUUID())
        .role(role)
        .cpf("12345678901")
        .rg("111111111")
        .email("patient@patient.com")
        .phone("45999999999")
        .fullName("Paciente da Silva")
        .birthdate(Instant.now())
        .mothersName("Mãe do Paciente")
        .sex('M')
        .placeOfBirth("Cascavel")
        .addressCountry("Brasil")
        .addressLine2("Casa")
        .password("senha0123")
        .active(true)
        .healthInsurance(healthInsurance)
        .build();
  }

  @Test
  @DisplayName("Save User When Successful")
  void save_user_whenSuccessful() {
    User userToBeSaved = createUser();

    User userSaved = repository.save(userToBeSaved);

    assertThat(userSaved).isNotNull();
    assertThat(userSaved.getEmail()).isEqualTo(userToBeSaved.getEmail());
  }

  @Test
  @DisplayName("Find User By Id When Successful")
  void find_userById_whenSuccessful() {
    User userToBeSaved = createUser();

    User userSaved = repository.save(userToBeSaved);

    Long id = userSaved.getId();

    Optional<User> userFound = repository.findById(id);

    assertThat(userFound).isNotNull().isNotEmpty();

    assertThat(userFound.get()).isEqualTo(userSaved);
  }

  @Test
  @DisplayName("Find User By Email When Successful")
  void find_userByEmail_whenSuccessful() {
    User userToBeSaved = createUser();

    User userSaved = repository.save(userToBeSaved);

    String email = userSaved.getEmail();

    Optional<User> userFound = repository.findByEmail(email);

    assertThat(userFound).isNotNull().isNotEmpty();

    assertThat(userFound.get()).isEqualTo(userSaved);
  }

  @Test
  @DisplayName("Find User By UUID When Successful")
  void find_userByUuid_whenSuccessful() {
    User userToBeSaved = createUser();

    User userSaved = repository.save(userToBeSaved);

    UUID uuid = userSaved.getUuid();

    Optional<User> userFound = repository.findByUuid(uuid);

    assertThat(userFound).isNotNull().isNotEmpty();

    assertThat(userFound.get()).isEqualTo(userSaved);
  }

  @Test
  @DisplayName("Find User By Cpf When Successful")
  void find_userByCpf_whenSuccessful() {
    User userToBeSaved = createUser();

    User userSaved = repository.save(userToBeSaved);

    String cpf = userSaved.getCpf();

    Optional<User> userFound = repository.findByCpf(cpf);

    assertThat(userFound).isNotNull().isNotEmpty();

    assertThat(userFound.get()).isEqualTo(userSaved);
  }

  @Test
  @DisplayName("Find Users By Full Name Containing Ignore Case When Successful")
  void find_userByFullNameContainingIgnoreCase_whenSuccessful() {
    User userToBeSave = createUser();

    User userSaved = repository.save(userToBeSave);

    String name = "Paciente";

    List<User> usersFound = repository.findByFullNameContainingIgnoreCase(name);
    System.out.println(usersFound);

    assertThat(usersFound).isNotNull().isNotEmpty().contains(userSaved);
  }

  @Test
  @DisplayName("Find Users By Role When Successful")
  void find_userByRole_whenSuccessful() {
    User userToBeSaved = createUser();

    User userSaved = repository.save(userToBeSaved);

    Optional<RoleEntity> role = roleRepository.findByRoleName(Role.ROLE_PATIENT);

    if (role.isPresent()) {
      List<User> usersFound = repository.findByRole(role.get());
      assertThat(usersFound).isNotNull().isNotEmpty().contains(userSaved);
    }
  }

  @Test
  @DisplayName("Find Users When Successful")
  void find_users_whenSuccessful() {
    User userToBeSaved = createUser();

    User userSaved = repository.save(userToBeSaved);

    List<User> usersFound = repository.findAll();

    assertThat(usersFound).isNotNull().isNotEmpty().contains(userSaved);
  }

  @Test
  @DisplayName("Update User When Successful")
  void update_user_whenSuccessful() {
    User userToBeSaved = createUser();
    User userToCompare = createUser();
    User userSaved = repository.save(userToBeSaved);

    userSaved.setFullName("Paciente Andrade");

    User userUpdated = repository.save(userSaved);

    assertThat(userUpdated.getFullName())
        .isNotEqualTo(userToCompare.getFullName())
        .isEqualTo("Paciente Andrade");
  }

  @Test
  @DisplayName("Delete User When Successful")
  void delete_user_whenSuccessful() {
    User userToBeSaved = createUser();

    User userSaved = repository.save(userToBeSaved);

    repository.delete(userSaved);

    List<User> userListEmpty = repository.findAll();

    assertThat(userListEmpty).doesNotContain(userSaved).hasSize(0).isEmpty();
  }
}
