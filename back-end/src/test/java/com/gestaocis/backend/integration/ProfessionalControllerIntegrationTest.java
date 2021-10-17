package com.gestaocis.backend.integration;

import com.gestaocis.backend.BackEndApplication;
import com.gestaocis.backend.DTOs.ProfessionalDTOs.NewProfessionalRequestDTO;
import com.gestaocis.backend.DTOs.ProfessionalDTOs.ProfessionalResponseDTO;
import com.gestaocis.backend.DTOs.ProfessionalDTOs.NewProfessionalRequestDTO;
import com.gestaocis.backend.DTOs.ProfessionalDTOs.ProfessionalResponseDTO;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.AddressRepository;
import com.gestaocis.backend.repositories.RoleEntityRepository;
import com.gestaocis.backend.repositories.UserRepository;
import com.gestaocis.backend.util.ProfessionalCreator;
import com.gestaocis.backend.util.ProfessionalCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = BackEndApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfessionalControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RoleEntityRepository roleEntityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    private UUID uuid;

    @BeforeEach
    void setUp() throws Exception {
        Address address = addressRepository.save(Address.builder()
                .cep("88958100")
                .street("rua tal")

                .city("cidade tal")
                .uf("parana")
                .neighborhood("bairro tal")
                .build());

        User userToBeSaved = ProfessionalCreator.createProfessionalToBeSaved();
        userToBeSaved.setAddress(address);
        userRepository.save(userToBeSaved);

        uuid = userToBeSaved.getUuid();
    }

    @Test
    @DisplayName("save Returns Saved Professional DTO when successful")
    public void save_returnsSavedProfessionalDto_whenSuccessful(){
        // Optional<RoleEntity> role = roleEntityRepository.findByRoleName(Role.ROLE_SECRETARY);
        // log.info(role.toString());

        NewProfessionalRequestDTO professionalToBeCreatedDTO = NewProfessionalRequestDTO.builder()
                .cpf("99999999991")
                .rg("88888888881")
                .email("test2@test.com")
                .phone("(45)9995595959")
                .fullName("João Teste da Silva")
                .birthdate("1997-10-30")
                .sex('M')
                .addressCountry("Brazel")
                .addressLine2("Rua da minha casa")
                .password("senha123")
                .cep("85858150")
                .street("Rua teste")
                .city("Foz do Iguaçu")
                .uf("PR")
                .neighborhood("Bairro da minha casa")
                .build();

        ResponseEntity<ProfessionalResponseDTO> response = testRestTemplate
                .postForEntity("/api/admin/secretaries", professionalToBeCreatedDTO, ProfessionalResponseDTO.class);

        Assertions.assertThat(response).isNotNull();

        Assertions.assertThat(response.getStatusCode().value()).isEqualTo(201);
        Assertions.assertThat(Objects.requireNonNull(response.getBody()).getEmail()).isEqualTo(professionalToBeCreatedDTO.getEmail());
    }


}