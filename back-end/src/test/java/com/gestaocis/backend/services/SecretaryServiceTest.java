package com.gestaocis.backend.services;

import com.gestaocis.backend.DTOs.SecretaryDTOs.SecretaryResponseDTO;
import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.RoleEntityRepository;
import com.gestaocis.backend.repositories.UserRepository;
import com.gestaocis.backend.util.AddressCreator;
import com.gestaocis.backend.util.SecretaryCreator;
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
class SecretaryServiceTest {

    @InjectMocks
    private SecretaryService secretaryService;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private RoleEntityRepository roleEntityRepositoryMock;

    @Mock
    private AddressService addressServiceMock;


    @BeforeEach
    void setUp() throws Exception {

        List<User> userList = new ArrayList<>();

        userList.add(SecretaryCreator.createValidUserSecretary());

        BDDMockito.when(roleEntityRepositoryMock.findByRoleName(Role.ROLE_SECRETARY))
                        .thenReturn(Optional.of(RoleEntity.builder().roleName(Role.ROLE_SECRETARY).build()));

        BDDMockito.when(addressServiceMock.save(ArgumentMatchers.any()))
                        .thenReturn(AddressCreator.createAddress(3));

        BDDMockito.when(userRepositoryMock.save(ArgumentMatchers.any()))
                .thenReturn(SecretaryCreator.createValidUserSecretary());

        BDDMockito.when(userRepositoryMock.findByUuid(ArgumentMatchers.any()))
                .thenReturn(Optional.of(SecretaryCreator.createValidUserSecretary()));

        BDDMockito.when(userRepositoryMock.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(SecretaryCreator.createValidUserSecretary()));

        BDDMockito.when(userRepositoryMock.findByCpf(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(SecretaryCreator.createValidUserSecretary()));

        BDDMockito.when(userRepositoryMock.findByFullNameContainingIgnoreCase(ArgumentMatchers.anyString()))
                .thenReturn(userList);

        BDDMockito.when(userRepositoryMock.findByRole(ArgumentMatchers.any()))
                .thenReturn(userList);

        BDDMockito.doNothing().when(userRepositoryMock).delete(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("save Return Saved Secretary when successful")
    public void save_returnSavedSecretary_whenSuccessful() throws Exception {
        String email = SecretaryCreator.createValidUserSecretary().getEmail();

        SecretaryResponseDTO response = secretaryService.save(SecretaryCreator.createValidSecretaryRequestDtoToBeSaved());

        Assertions.assertThat(response)
                .isNotNull();

        Assertions.assertThat(response.getEmail())
                .isEqualTo(email);
    }

    @Test
    @DisplayName("findByUUID Return Secretary when successful")
    public void findByUUID_returnSecretary_whenSuccessful(){
        String email = SecretaryCreator.createValidUserSecretary().getEmail();

        SecretaryResponseDTO response = secretaryService.findByUUID(UUID.randomUUID());

        Assertions.assertThat(response)
                .isNotNull();

        Assertions.assertThat(response.getEmail())
                .isEqualTo(email);
    }

    @Test
    @DisplayName("findByEmail Return Secretary when successful")
    public void findByEmail_returnSecretary_whenSuccessful(){
        String email = SecretaryCreator.createValidUserSecretary().getEmail();

        SecretaryResponseDTO response = secretaryService.findByEmail("test@test.com");

        Assertions.assertThat(response)
                .isNotNull();

        Assertions.assertThat(response.getEmail())
                .isEqualTo(email);
    }

    @Test
    @DisplayName("findByFullName Return List of Secretary when successful")
    public void findByFullName_returnListOfSecretary_whenSuccessful(){
        String email = SecretaryCreator.createValidUserSecretary().getEmail();

        List<SecretaryResponseDTO> response = secretaryService.findByFullName("test@test.com");

        Assertions.assertThat(response)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(response.get(0).getEmail())
                .isEqualTo(email);
    }

    @Test
    @DisplayName("findByRole Return List of Secretary when successful")
    public void findByRole_returnListOfSecretary_whenSuccessful(){
        String email = SecretaryCreator.createValidUserSecretary().getEmail();

        List<SecretaryResponseDTO> response = secretaryService.findByRole();

        Assertions.assertThat(response)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(response.get(0).getEmail())
                .isEqualTo(email);
    }

    @Test
    @DisplayName("findByCpf Return Secretary when successful")
    public void findByCpf_returnSecretary_whenSuccessful(){
        String email = SecretaryCreator.createValidUserSecretary().getEmail();

        SecretaryResponseDTO response = secretaryService.findByCpf("99999999");

        Assertions.assertThat(response)
                .isNotNull();

        Assertions.assertThat(response.getEmail())
                .isEqualTo(email);
    }

    @Test
    @DisplayName("update Return Saved Secretary when successful")
    public void update_returnUpdatedSecretary_whenSuccessful() throws Exception {
        String email = SecretaryCreator.createValidUserSecretary().getEmail();

        SecretaryResponseDTO response = secretaryService.update(UUID.randomUUID(),SecretaryCreator.createValidSecretaryRequestDtoToBeSaved());

        Assertions.assertThat(response)
                .isNotNull();

        Assertions.assertThat(response.getEmail())
                .isEqualTo(email);
    }

    @Test
    @DisplayName("Delete Return true when Successful")
    public void delete_returnTrue_whenSuccessful(){

        Boolean response = secretaryService.delete(UUID.randomUUID());

        Assertions.assertThat(response)
                .isNotNull();
    }
}


























