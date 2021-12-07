package com.cis.service;

import com.cis.model.dto.AdminDTO.AdminReturnDTO;
import com.cis.repository.AdminRepository;
import com.cis.utils.AdminCreator;
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

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class AdminServiceTest {

    @InjectMocks
    private AdminService service;

    @Mock
    private AdminRepository repository;


    @BeforeEach
    void setup(){
        BDDMockito.when(repository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(AdminCreator.creatAdminSaved()));

        BDDMockito.when(repository.save(ArgumentMatchers.any()))
                .thenReturn(AdminCreator.creatAdminSaved());

        BDDMockito.when(repository.getById(ArgumentMatchers.any()))
                .thenReturn(AdminCreator.creatAdminSaved());

        BDDMockito.doNothing().when(repository).delete(ArgumentMatchers.any());

    }

    @Test
    @DisplayName("Should return Admin by ID when successful")
    void test_findById(){
        AdminReturnDTO id = service.findByIdOrThrowError(UUID.randomUUID());

        Assertions.assertThat(id).isNotNull();
        Assertions.assertThat(id.getEmail()).isEqualTo(AdminCreator.creatAdminSaved().getEmail());
    }

    @Test
    @DisplayName("Should create Admin when successful")
    void test_create(){
        BDDMockito.when(repository.findByEmail(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        AdminReturnDTO adminReturnDTO = service.save(AdminCreator.createAdminDTOToBeSaved());

        Assertions.assertThat(adminReturnDTO).isNotNull();
        Assertions.assertThat(adminReturnDTO.getEmail()).isEqualTo(AdminCreator.createAdminDTOToBeSaved().getEmail());
    }

    @Test
    @DisplayName("Should update Admin when successful")
    void test_update() {
        String update = service.update(UUID.randomUUID(), AdminCreator.adminDTOToBeUpdated());
        Assertions.assertThat(update).isNotNull();
    }

    @Test
    @DisplayName("Should delete Admin when successful")
    void test_delete(){
        Assertions.assertThatCode(() -> service.delete(UUID.randomUUID())).doesNotThrowAnyException();
    }

}