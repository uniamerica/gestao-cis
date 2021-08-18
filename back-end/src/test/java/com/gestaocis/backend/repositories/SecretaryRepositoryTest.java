package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Secretary;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for secretary repository")
class SecretaryRepositoryTest {

    @Autowired
    private SecretaryRepository secretaryRepository;

    private Secretary createFakeSecretaryData(){
        Secretary secretary = new Secretary();
        UUID secretaryID = new UUID(1L,1L);
        secretary.setSecretaryId(secretaryID);
        secretary.setCpf("ashjdaudhasud");
        secretary.setEmail("eaushduahaid");
        secretary.setFullName("tasidjasisdjasoid");
        secretary.setRg("asjdiajssdisa");
        secretary.setPassword("oaskdoaksdoaskd");

        return secretary;
    }


    @Test
    @DisplayName("Save Secretary When Successful")
    void save_persistSecretary_whenSuccessful(){
        Secretary fakeSecretary = createFakeSecretaryData();
        Secretary savedSecretary = secretaryRepository.save(fakeSecretary);

        // verificar se o objeto não está vazio.
        Assertions.assertThat(savedSecretary).isNotNull();

        //verificar se o objeto salvo é igual ao objeto mockado.
        Assertions.assertThat(savedSecretary).isEqualTo(fakeSecretary);
    }

    @Test
    @DisplayName("Find Secretary By Id When Successful")
    void list_FindSecretaryById_whenSuccessful() {

        Secretary fakeSecretary = createFakeSecretaryData();
        Secretary savedSecretary = secretaryRepository.save(fakeSecretary);

        Optional<Secretary> secretaryInDatabase = secretaryRepository.findById(savedSecretary.getId());

        // Verificar se o objeto retornado não está vazio.
        Assertions.assertThat(secretaryInDatabase.isEmpty()).isFalse();

        // Verificar se os id do objeto retornado é igual ao do objeto salvo no banco.
        Assertions.assertThat(secretaryInDatabase.get().getSecretaryId()).isEqualTo(savedSecretary.getSecretaryId());

    }

    @Test
    @DisplayName("List of Secretaries When Successful")
    void list_returnsListOfSecretaries_whenSuccessful() {

        Secretary fakeSecretary = createFakeSecretaryData();
        Secretary savedSecretary = secretaryRepository.save(fakeSecretary);

        List<Secretary> ListOfSecretaries = secretaryRepository.findAll();


        // Verificar se o objeto retornado não é nulo.
        Assertions.assertThat(ListOfSecretaries).isNotNull();

        // Verificar se o objeto salvo está na lista retornada.
        Assertions.assertThat(ListOfSecretaries.contains(savedSecretary)).isTrue();

    }


    @Test
    @DisplayName("Update Secretary When Successful")
    void update_returnUpdatedSecretary_WhenSuccessful() {

        Secretary fakeSecretary = createFakeSecretaryData();
        Secretary savedSecretary = secretaryRepository.save(fakeSecretary);

        savedSecretary.setFullName("New Name");

        Secretary updatedSecretary = secretaryRepository.save(savedSecretary);

        // Verificar se o objeto não é nulo.
        Assertions.assertThat(updatedSecretary).isNotNull();

        // Verificar se os objetos pussuem o mesmo ID.
        Assertions.assertThat(updatedSecretary.getId()).isEqualTo(savedSecretary.getId());
    }

    @Test
    @DisplayName("Delete Secretary When Successful")
    void delete_deleteSecretary_WhenSuccessful() {

        Secretary fakeSecretary = createFakeSecretaryData();
        Secretary savedSecretary = secretaryRepository.save(fakeSecretary);

        secretaryRepository.delete(savedSecretary);

        Optional<Secretary> nullSecretary = secretaryRepository.findById(savedSecretary.getId());

        // Verificar se o objeto retornado é vazio.
        Assertions.assertThat(nullSecretary).isEmpty();

    }


}