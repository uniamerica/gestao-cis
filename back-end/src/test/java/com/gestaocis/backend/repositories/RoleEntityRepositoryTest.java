package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.RoleEntity;
import com.gestaocis.backend.utils.enums.Role;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Log4j2
class RoleEntityRepositoryTest {

    @Autowired
    private RoleEntityRepository roleEntityRepository;

    private RoleEntity createRole(Role role){
        return RoleEntity.builder()
                .roleName(role)
                .build();
    }

    @Test
    @DisplayName("Save all Roles when Successful")
    public void save_allRoles_whenSuccessful(){
        List<RoleEntity> roles = new ArrayList<>();
        for(Role r : Role.values()){
            roles.add(createRole(r));
        }
         List<RoleEntity> rolesSaved = roleEntityRepository.saveAll(roles);

        Assertions.assertThat(rolesSaved).isNotNull();
        Assertions.assertThat(rolesSaved).isNotEmpty();
    }

    @Test
    @DisplayName("Find List of Roles when Successful")
    public void find_ListOfRoles_whenSuccessful(){
        List<RoleEntity> roles = new ArrayList<>();
        for(Role r : Role.values()){
            roles.add(createRole(r));
        }
        roleEntityRepository.saveAll(roles);

        List<RoleEntity> rolesList = roleEntityRepository.findAll();

        Assertions.assertThat(rolesList).isNotNull();
        Assertions.assertThat(rolesList).isNotEmpty();
    }

    @Test
    @DisplayName("Find Role by RoleName when Successful")
    public void find_roleByRoleName_whenSuccessful(){
        List<RoleEntity> roles = new ArrayList<>();
        for(Role r : Role.values()){
            roles.add(createRole(r));
        }
        roleEntityRepository.saveAll(roles);

        Optional<RoleEntity> role = roleEntityRepository.findByRoleName(Role.ROLE_PROFESSIONAL);


        Assertions.assertThat(role).isNotNull();
        Assertions.assertThat(role.get().getRoleName()).isEqualTo(Role.ROLE_PROFESSIONAL);
    }




}