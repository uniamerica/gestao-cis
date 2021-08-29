package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.RoleEntity;
import com.gestaocis.backend.models.SpecialtyEntity;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.utils.enums.Role;
import com.gestaocis.backend.utils.enums.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleEntityRepository roleEntityRepository;

    @Autowired
    private SpecialtyEntityRepository specialtyEntityRepository;

    private User createUser(){
       RoleEntity role = roleEntityRepository
               .save(RoleEntity.builder().roleName(Role.ROLE_PROFESSIONAL).build());

       SpecialtyEntity specialty = specialtyEntityRepository
               .save(SpecialtyEntity.builder().specialtyName(Specialty.SPECIALTY_NUTRITION).build());

       return User.builder()
               .role(role)
               .specialty(specialty)
               .professionalDocument("xxxxxxxx")
               .phone("(45)99999999")
               .email("teste@test.com")
               .cpf("898989898989")
               .rg("88889888898")
               .fullName("Fulano de Tal")
               .password("senha1234")
                .build();

   }

   @Test
   @DisplayName("Save User When Successful")
    void save_user_whenSuccessful(){
        User userToBeSave = createUser();

   }

}