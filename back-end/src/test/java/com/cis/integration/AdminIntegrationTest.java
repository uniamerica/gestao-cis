package com.cis.integration;

import com.cis.model.Admin;
import com.cis.model.dto.AdminDTO.AdminReturnDTO;
import com.cis.model.dto.AdminDTO.AdminUpdateDTO;
import com.cis.model.dto.UserDTO.AuthenticationRequest;
import com.cis.repository.AdminRepository;
import com.cis.utils.AdminCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class AdminIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AdminRepository repository;

    @Test
    @DisplayName("SAVE - Admin Integration Test")
    public void test_save() {
        ResponseEntity<AdminReturnDTO> save = testRestTemplate.exchange("/api/admins", HttpMethod.POST, new HttpEntity<>(AdminCreator.createAdminDTOToBeSaved()), AdminReturnDTO.class);

        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("LOGIN - Admin Integration Test")
    public void test_login(){
        Admin saved = repository.save(AdminCreator.createAdminToBeSaved());

        AuthenticationRequest auth = AuthenticationRequest
                .builder()
                .email("teste@admin.com")
                .password("senhaFortissima")
                .build();


        ResponseEntity<Object> login = testRestTemplate.exchange("/api/admins/login", HttpMethod.POST, new HttpEntity<>(auth), Object.class);
        Assertions.assertThat(login).isNotNull();
        Assertions.assertThat(login.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    @DisplayName("FIND BY ID - Admin Integration")
    public void test_findById(){
        Admin saved = repository.save(AdminCreator.createAdminToBeSaved());

        ResponseEntity<AdminReturnDTO> findById = testRestTemplate.exchange("/api/admins/{id}", HttpMethod.GET, null, AdminReturnDTO.class, saved.getId());
        Assertions.assertThat(findById).isNotNull();
        Assertions.assertThat(findById.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @DisplayName("UPDATE - Admin Integration")
    public void test_update(){
        Admin saved = repository.save(AdminCreator.createAdminToBeSaved());

        AdminUpdateDTO updateDto = AdminCreator.adminDTOToBeUpdated();
        updateDto.setPhone("(45) 999 155 581");

        ResponseEntity<String> update = testRestTemplate.exchange("/api/admins/{id}", HttpMethod.PUT, new HttpEntity<>(updateDto), String.class, saved.getId());
        Assertions.assertThat(update).isNotNull();
        Assertions.assertThat(update.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("DELETE - Admin Integration")
    public void test_delete(){
        Admin saved = repository.save(AdminCreator.createAdminToBeSaved());

        ResponseEntity<String> delete = testRestTemplate.exchange("/api/admins/{id}", HttpMethod.DELETE, null, String.class, saved.getId());
        Assertions.assertThat(delete).isNotNull();
        Assertions.assertThat(delete.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
