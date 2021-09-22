package com.gestaocis.backend.repositories;

import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.enums.SpecialtyEntity;
import com.gestaocis.backend.models.HealthInsurance;
import com.gestaocis.backend.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
class UserRepositoryTest {

  @Autowired private UserRepository userRepository;

  @Autowired private RoleEntityRepository roleEntityRepository;

  @Autowired private SpecialtyEntityRepository specialtyEntityRepository;

  @Autowired private HealthInsuranceRepository healthInsuranceRepository;

  private User createUser() {
    RoleEntity role =
        roleEntityRepository
                .save(RoleEntity.builder().roleName(Role.ROLE_PROFESSIONAL).build());

    SpecialtyEntity specialty =
        specialtyEntityRepository.save(
            SpecialtyEntity.builder().specialtyName("FISIOTERAPIA").build());

    List<SpecialtyEntity> specialtyEntities = new ArrayList<>();
    specialtyEntities.add(specialty);

    HealthInsurance healthInsurance = healthInsuranceRepository.save(
            HealthInsurance
                    .builder()
                    .insuranceName("ashuahdisuahdisuahd")
                    .registrationNumber("lalalalalalalalalla")
                    .build()
    );


    return User.builder()
            .role(role)
            .cpf("898989898989")
            .rg("88889888898")
            .professionalDocument("xxxxxxxx")
            .specialties(specialtyEntities)
            .email("test@test.com")
            .phone("(45)99999999")
            .email("teste@test.com")
            .fullName("Fulano de Tal")
            .birthdate(Instant.now())
            .mothersName("Mãe do Fulano")
            .sex('M')
            .placeOfBirth("Brazel")
            .addressCountry("Brasil")
            .addressLine2("Rua tal")
            .password("senha1234")
            .active(true)
            .healthInsurance(healthInsurance)
            .build();
  }

  @Test
  @DisplayName("Save User When Successful")
  void save_user_whenSuccessful() {
    User userToBeSave = createUser();

    User userSaved = userRepository.save(userToBeSave);

    Assertions.assertThat(userSaved).isNotNull();
    Assertions.assertThat(userSaved.getEmail()).isEqualTo(userToBeSave.getEmail());
  }

  @Test
  @DisplayName("Find User By Id When Successful")
  void find_userById_whenSuccessful() {
    User userToBeSave = createUser();

    User userSaved = userRepository.save(userToBeSave);

    Long id = userSaved.getId();

    Optional<User> userFound = userRepository.findById(id);

    Assertions.assertThat(userFound).isNotNull().isNotEmpty();

    Assertions.assertThat(userFound.get()).isEqualTo(userSaved);
  }

  @Test
  @DisplayName("Find User By Email When Successful")
  void find_userByEmail_whenSuccessful() {
    User userToBeSave = createUser();

    User userSaved = userRepository.save(userToBeSave);

    String email = userSaved.getEmail();

    Optional<User> userFound = userRepository.findByEmail(email);

    Assertions.assertThat(userFound).isNotNull().isNotEmpty();

    Assertions.assertThat(userFound.get()).isEqualTo(userSaved);
  }

  @Test
  @DisplayName("Find User By Uuid When Successful")
  void find_userByUuid_whenSuccessful() {
    User userToBeSave = createUser();

    User userSaved = userRepository.save(userToBeSave);

    UUID uuid = userSaved.getUuid();

    Optional<User> userFound = userRepository.findByUuid(uuid);

    Assertions.assertThat(userFound).isNotNull().isNotEmpty();

    Assertions.assertThat(userFound.get()).isEqualTo(userSaved);
  }

  @Test
  @DisplayName("Find User By Cpf When Successful")
  void find_userByCpf_whenSuccessful() {
    User userToBeSave = createUser();

    User userSaved = userRepository.save(userToBeSave);

    String cpf = userSaved.getCpf();

    Optional<User> userFound = userRepository.findByCpf(cpf);

    Assertions.assertThat(userFound).isNotNull().isNotEmpty();

    Assertions.assertThat(userFound.get()).isEqualTo(userSaved);
  }

  @Test
  @DisplayName("Find Users By Full Name Containing Ignore Case When Successful")
  void find_userByFullNameContainingIgnoreCase_whenSuccessful() {
    User userToBeSave = createUser();

    User userSaved = userRepository.save(userToBeSave);

    String name = "tAl";

    List<User> usersFound = userRepository.findByFullNameContainingIgnoreCase(name);

    Assertions.assertThat(usersFound).isNotNull().isNotEmpty().contains(userSaved);
  }

  @Test
  @DisplayName("Find Users By Role When Successful")
  void find_userByRole_whenSuccessful() {
    User userToBeSave = createUser();

    User userSaved = userRepository.save(userToBeSave);

    Optional<RoleEntity> role = roleEntityRepository.findByRoleName(Role.ROLE_PROFESSIONAL);

    List<User> usersFound = userRepository.findByRole(role.get());

    Assertions.assertThat(usersFound).isNotNull().isNotEmpty().contains(userSaved);
  }

<<<<<<< HEAD
  @Test
  @DisplayName("Find Users By Specialty When Successful")
  void find_userBySpecialty_whenSuccessful() {
    User userToBeSave = createUser();

    User userSaved = userRepository.save(userToBeSave);

    Optional<SpecialtyEntity> specialty =
        specialtyEntityRepository.findBySpecialtyNameIgnoreCase("FISIOTERAPIA");

    List<User> usersFound = userRepository.findBySpecialties(specialty.get());

    Assertions.assertThat(usersFound).isNotNull().isNotEmpty().contains(userSaved);
  }
=======
>>>>>>> 73df63ee4dc478bc27323dfe5d302833e262a390

  @Test
  @DisplayName("Find Users When Successful")
  void find_users_whenSuccessful() {
    User userToBeSave = createUser();

    User userSaved = userRepository.save(userToBeSave);

    List<User> usersFound = userRepository.findAll();

    Assertions.assertThat(usersFound).isNotNull().isNotEmpty().contains(userSaved);
  }

  @Test
  @DisplayName("Update User When Successful")
  void update_user_whenSuccessful() {
    User userToBeSave = createUser();
    User userToCompare = createUser();
    User userSaved = userRepository.save(userToBeSave);

    userSaved.setFullName("lalalalalala");

    User userUpdated = userRepository.save(userSaved);

    Assertions.assertThat(userUpdated.getFullName()).isNotEqualTo(userToCompare.getFullName());
  }

  @Test
  @DisplayName("Delete User When Successful")
  void delete_user_whenSuccessful() {
    User userToBeSave = createUser();

    User userSaved = userRepository.save(userToBeSave);

    userRepository.delete(userSaved);

    List<User> userListEmpty = userRepository.findAll();

    Assertions.assertThat(userListEmpty).doesNotContain(userSaved);
  }
}
