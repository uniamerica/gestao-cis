package com.cis.controller;

import com.cis.model.dto.AdminDTO.AdminReturnDTO;
import com.cis.service.AdminService;
import com.cis.utils.AdminCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class AdminControllerTest {

    @InjectMocks
    private AdminController controller;

    @Mock
    private AdminService service;

    @Test
    @DisplayName("Should create Admin when successfull")
    public void test_create() throws Exception {
        BDDMockito.when(service.save(ArgumentMatchers.any()))
                .thenReturn(AdminCreator.createAdminDTOSaved());

        ResponseEntity<AdminReturnDTO> healthProfessionalResponseDTOResponseEntity = controller.save(AdminCreator.createAdminDTOToBeSaved());

        Assertions.assertThat(healthProfessionalResponseDTOResponseEntity).isNotNull();
        Assertions.assertThat(healthProfessionalResponseDTOResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("Should delete Admin when successful")
    public void test_delete() {
        Assertions.assertThatCode(() -> controller.delete(UUID.randomUUID())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Should return Admin when successful")
    public void test_findById() throws Exception {
        BDDMockito.when(service.findByIdOrThrowError(ArgumentMatchers.any()))
                .thenReturn(AdminCreator.createAdminDTOSaved());

        ResponseEntity<AdminReturnDTO> adminReturnDTOResponseEntity = controller.findById(UUID.randomUUID());

        Assertions.assertThat(adminReturnDTOResponseEntity).isNotNull();
        Assertions.assertThat(adminReturnDTOResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Should update Admin when successful")
    public void test_update() throws Exception {
        var creator = AdminCreator.createAdminDTOSaved();
        creator.setEmail("teste@creator.com");

        BDDMockito.when(service.update(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(String.valueOf(creator));


        ResponseEntity<String> update = controller.update(UUID.randomUUID(), AdminCreator.adminDTOToBeUpdated());

        Assertions.assertThat(update).isNotNull();
        Assertions.assertThat(update.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
