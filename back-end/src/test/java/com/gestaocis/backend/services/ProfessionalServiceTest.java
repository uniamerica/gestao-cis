package com.gestaocis.backend.services;

import com.gestaocis.backend.DTOs.ProfessionalDTO.ProfessionalResponseDTO;
import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.enums.Specialty;
import com.gestaocis.backend.enums.SpecialtyEntity;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.RoleEntityRepository;
import com.gestaocis.backend.repositories.SpecialtyEntityRepository;
import com.gestaocis.backend.repositories.UserRepository;
import com.gestaocis.backend.util.ProfessionalCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ProfessionalServiceTest {

    @InjectMocks
    private ProfessionalService professionalService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleEntityRepository roleEntityRepository;

    @Mock
    private SpecialtyEntityRepository specialtyEntityRepository;

    @BeforeEach
    void setUp() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(ProfessionalCreator.createValidUserProfessional());

        BDDMockito.when(roleEntityRepository.findByRoleName(ArgumentMatchers.any()))
                .thenReturn(Optional.of(RoleEntity.builder().roleName(Role.ROLE_PROFESSIONAL).build()));

        BDDMockito.when(specialtyEntityRepository.findByUuid(ArgumentMatchers.any()))
                        .thenReturn(Optional.of(SpecialtyEntity.builder().specialtyName(Specialty.SPECIALTY_ACUPUNCTURE.getSpecialtyValue()).build()));

        BDDMockito.when(userRepository.save(ArgumentMatchers.any()))
                .thenReturn(ProfessionalCreator.createValidUserProfessional());

    }

    @Test
    @DisplayName("Sava Return Saved Professional when successful")
    public void should_createValidProfessional_whenSuccessful(){
        String email = ProfessionalCreator.createValidUserProfessional().getEmail();

        ProfessionalResponseDTO professionalResponseDTO = professionalService.create(ProfessionalCreator.createValidProfessionalRequestDTOToBeSaved());

        Assertions.assertThat(professionalResponseDTO).isNotNull();
        Assertions.assertThat(professionalResponseDTO.getEmail()).isEqualTo(email);

    }


}