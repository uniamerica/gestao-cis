package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Secretary;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
    @DisplayName("Deve persistir o dado quando bem-sucessido")
    void persistFakeSecretaryInDatabase(){
        Secretary fakeSecretary = createFakeSecretaryData();
        Secretary savedSecretary = secretaryRepository.save(fakeSecretary);

        // verificar se o objeto não está vazio.
        Assertions.assertThat(savedSecretary).isNotNull();

        //verificar se o objeto salvo é igual ao objeto mockado.
        Assertions.assertThat(savedSecretary).isEqualTo(fakeSecretary);
    }

    // TODO: TESTE DE GET BY ID

    // TODO: TESTE DE GET LISTA DE SECRETARIAS

    // TODO: TESTE DE PUT

    // TODO: TESTE DE DELETE


}