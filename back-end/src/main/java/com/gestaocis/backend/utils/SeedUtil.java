package com.gestaocis.backend.utils;

import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.AddressRepository;
import com.gestaocis.backend.repositories.RoleEntityRepository;
import com.gestaocis.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class SeedUtil {

  @Autowired private UserRepository userRepository;

  @Autowired private RoleEntityRepository roleEntityRepository;

  @Autowired private AddressRepository addressRepository;

  //    @Value("${manager.password}")
  @Value("admin")
  private String password;

  @EventListener(ApplicationReadyEvent.class)
  public void rolesAndManagerSeed() {

    if (userRepository.findByEmail("admin@cis.com.br").isEmpty()) {
      List<RoleEntity> roles = new ArrayList<>();
      try {
        for (Role role : Role.values()) {
          RoleEntity entity = RoleEntity.builder().roleName(role).build();

          roles.add(entity);
        }

        roleEntityRepository.saveAll(roles);

        Address addressToBeSaved =
            addressRepository.save(
                Address.builder()
                    .cep("85853-260")
                    .street("Felipe Wandscheer")
                    .city("Foz do Iguaçu")
                    .uf("PR")
                    .neighborhood("Vila Yolanda")
                    .build());

        RoleEntity role = roleEntityRepository.findByRoleName(Role.ROLE_MANAGER).get();

        userRepository.save(
            User.builder()
                .cpf("00000000000")
                .rg("000000000000")
                .uuid(UUID.randomUUID())
                .email("admin@cis.com.br")
                .role(role)
                .phone("45) 2105-9099")
                .fullName("Cis Admin")
                .birthdate(Instant.now())
                .sex('N')
                .addressCountry("Brasil")
                .addressLine2("Avenida")
                .address(addressToBeSaved)
                .password(new BCryptPasswordEncoder().encode(password))
                .build());
      } catch (Exception e) {
        System.out.println("Erro ao colocar a Seed: " + e.getMessage());
      }
    }
  }
}
