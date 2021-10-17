package com.gestaocis.backend.services;

import com.gestaocis.backend.DTOs.PatientDTOs.PatientResponseDTO;
import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.RoleEntityRepository;
import com.gestaocis.backend.repositories.UserRepository;
import com.gestaocis.backend.util.AddressCreator;
import com.gestaocis.backend.util.PatientCreator;
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
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private RoleEntityRepository roleEntityRepositoryMock;

    @Mock
    private AddressService addressServiceMock;

    @BeforeEach
    void setUp() throws Exception {

        List<User> userList = new ArrayList<>();

        userList.add(PatientCreator.createValidUserPatient());

        BDDMockito.when(roleEntityRepositoryMock.findByRoleName(Role.ROLE_PATIENT))
                .thenReturn(Optional.of(RoleEntity.builder().roleName(Role.ROLE_PATIENT).build()));

        BDDMockito.when(addressServiceMock.save(ArgumentMatchers.any()))
                .thenReturn(AddressCreator.createAddress(3));

        BDDMockito.when(userRepositoryMock.save(ArgumentMatchers.any()))
                .thenReturn(PatientCreator.createValidUserPatient());

        BDDMockito.when(userRepositoryMock.findByUuid(ArgumentMatchers.any()))
                .thenReturn(Optional.of(PatientCreator.createValidUserPatient()));

        BDDMockito.when(userRepositoryMock.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(PatientCreator.createValidUserPatient()));

        BDDMockito.when(userRepositoryMock.findByCpf(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(PatientCreator.createValidUserPatient()));

        BDDMockito.when(userRepositoryMock.findByFullNameContainingIgnoreCase(ArgumentMatchers.anyString()))
                .thenReturn(userList);

        BDDMockito.when(userRepositoryMock.findByRole(ArgumentMatchers.any()))
                .thenReturn(userList);

        BDDMockito.doNothing().when(userRepositoryMock).delete(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("save Return Saved Patient when successful")
    public void save_returnSavedPatient_whenSuccessful() throws Exception {
        String email = PatientCreator.createValidUserPatient().getEmail();

        PatientResponseDTO response = patientService.save(PatientCreator.createValidPatientRequestDtoToBeSaved());

        Assertions.assertThat(response)
                .isNotNull();

        Assertions.assertThat(response.getEmail())
                .isEqualTo(email);
    }

    @Test
    @DisplayName("findByUUID Return Patient when successful")
    public void findByUUID_returnPatient_whenSuccessful(){
        String email = PatientCreator.createValidUserPatient().getEmail();

        PatientResponseDTO response = patientService.findByUUID(UUID.randomUUID());

        Assertions.assertThat(response)
                .isNotNull();

        Assertions.assertThat(response.getEmail())
                .isEqualTo(email);
    }

    @Test
    @DisplayName("findByEmail Return Patient when successful")
    public void findByEmail_returnPatient_whenSuccessful(){
        String email = PatientCreator.createValidUserPatient().getEmail();

        PatientResponseDTO response = patientService.findByEmail("test@test.com");

        Assertions.assertThat(response)
                .isNotNull();

        Assertions.assertThat(response.getEmail())
                .isEqualTo(email);
    }

    @Test
    @DisplayName("findByFullName Return List of Patient when successful")
    public void findByFullName_returnListOfPatient_whenSuccessful(){
        String email = PatientCreator.createValidUserPatient().getEmail();

        List<PatientResponseDTO> response = patientService.findByFullName("test@test.com");

        Assertions.assertThat(response)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(response.get(0).getEmail())
                .isEqualTo(email);
    }

    @Test
    @DisplayName("findAll Return List of Patient when successful")
    public void findAll_returnListOfPatient_whenSuccessful(){
        String email = PatientCreator.createValidUserPatient().getEmail();

        List<PatientResponseDTO> response = patientService.findAll();

        Assertions.assertThat(response)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(response.get(0).getEmail())
                .isEqualTo(email);
    }

    @Test
    @DisplayName("findByCpf Return Patient when successful")
    public void findByCpf_returnPatient_whenSuccessful(){
        String email = PatientCreator.createValidUserPatient().getEmail();

        PatientResponseDTO response = patientService.findByCpf("99999999");

        Assertions.assertThat(response)
                .isNotNull();

        Assertions.assertThat(response.getEmail())
                .isEqualTo(email);
    }

    @Test
    @DisplayName("update Return Saved Patient when successful")
    public void update_returnUpdatedPatient_whenSuccessful() throws Exception {
        String email = PatientCreator.createValidUserPatient().getEmail();

        PatientResponseDTO response = patientService.update(UUID.randomUUID(),PatientCreator.createValidPatientRequestDtoToBeSaved());

        Assertions.assertThat(response)
                .isNotNull();

        Assertions.assertThat(response.getEmail())
                .isEqualTo(email);
    }

    @Test
    @DisplayName("Delete Return true when Successful")
    public void delete_returnTrue_whenSuccessful(){

        Boolean response = patientService.delete(UUID.randomUUID());

        Assertions.assertThat(response)
                .isNotNull();
    }
}
