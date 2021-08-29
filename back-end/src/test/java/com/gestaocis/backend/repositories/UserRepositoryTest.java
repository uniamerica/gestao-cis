package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.User;
import com.gestaocis.backend.utils.enums.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User createUserPatient(){
        return User.builder()
                .role(Role.ROLE_PATIENT)
                .phone("(45)90909090")
                .email("email@test.com")
                .cpf("999999999")
                .rg("8888888888")
                .fullName("Fulano de Sicrano")
                .password("senhaDoOrkut123")
            .build();
    }

    @Test
    @DisplayName("Save User Patient When Successful")
    void save_userPatient_whenSuccessful(){
        User userTest = createUserPatient();
        User userSaved = userRepository.save(userTest);

        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved).isEqualTo(userTest);

    }

    @Test
    @DisplayName("Find User By Id When Successful")
    void find_userById_WhenSuccessful(){
        User userTest = createUserPatient();
        User userSaved = userRepository.save(userTest);

        Optional<User> userInDatabase = userRepository.findById(userSaved.getId());

        Assertions.assertThat(userInDatabase.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Find User By uuid When Successful")
    void find_userByUuid_WhenSuccessful(){
        User userTest = createUserPatient();
        User userSaved = userRepository.save(userTest);

        Optional<User> userInDatabase = userRepository.findByUuid(userSaved.getUuid());

        Assertions.assertThat(userInDatabase.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Find User By Cpf When Successful")
    void find_userByCpf_WhenSuccessful(){
        User userTest = createUserPatient();
        User userSaved = userRepository.save(userTest);

        Optional<User> userInDatabase = userRepository.findByCpf(userSaved.getCpf());

        Assertions.assertThat(userInDatabase.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Find User By Email When Successful")
    void find_userByEmail_WhenSuccessful(){
        User userTest = createUserPatient();
        User userSaved = userRepository.save(userTest);

        Optional<User> userInDatabase = userRepository.findByEmail(userSaved.getEmail());

        Assertions.assertThat(userInDatabase.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Update User When Successful")
    void update_user_whenSuccessful(){
        User userTest = createUserPatient();
        User userToCompare = createUserPatient();

        User userSaved = userRepository.save(userTest);

        userSaved.setFullName("Sicrano de Fulano");

        User userUpdated = userRepository.save(userSaved);

        Assertions.assertThat(userUpdated).isNotNull();
        Assertions.assertThat(userUpdated.getId()).isEqualTo(userSaved.getId());
        Assertions.assertThat(userUpdated.getFullName()).isNotEqualTo(userToCompare.getFullName());


    }

    @Test
    @DisplayName("Delete User When Successful")
    void delete_user_whenSuccessful(){
        User userTest = createUserPatient();
        User userSaved = userRepository.save(userTest);

        userRepository.deleteById(userSaved.getId());

        List<User> usersEmptyList = userRepository.findAll();

        Assertions.assertThat(usersEmptyList).isNotNull();
        Assertions.assertThat(usersEmptyList.contains(userSaved)).isFalse();

    }

    @Test
    @DisplayName("Find List of Users when Successful")
    void find_usersList_whenSuccessful(){
        User userTest = createUserPatient();
        User userSaved = userRepository.save(userTest);

        List<User> usersList = userRepository.findAll();

        Assertions.assertThat(usersList).isNotNull();
        Assertions.assertThat(usersList.contains(userSaved)).isTrue();
    }

    @Test
    @DisplayName("Find List of Users that Contains String when Successful")
    void find_userListThatContainsString_whenSuccessful(){
        User userTest = createUserPatient();

        User userSaved = userRepository.save(userTest);

        List<User> userList = userRepository.findByFullNameContainingIgnoreCase("AnO");

        Assertions.assertThat(userList).isNotNull();

        Assertions.assertThat(userList.isEmpty()).isFalse();

        Assertions.assertThat(userList.contains(userSaved)).isTrue();

    }

}