package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.SpecialtyEntity;
import com.gestaocis.backend.utils.enums.Specialty;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class SpecialtyEntityRepositoryTest {

    @Autowired
    private SpecialtyEntityRepository specialtyEntityRepository;

    private SpecialtyEntity createSpecialty(Specialty specialty){
        return SpecialtyEntity.builder()
                .specialtyName(specialty)
                .build();
    }

    @Test
    @DisplayName("Save All Specialties in Database when successful")
    public void save_all_specialties_whenSuccessful(){
        List<SpecialtyEntity> specialties = new ArrayList<>();
        for(Specialty s : Specialty.values()){
            specialties.add(createSpecialty(s));
        }

        List<SpecialtyEntity> specialtiesSaved = specialtyEntityRepository.saveAll(specialties);

        Assertions.assertThat(specialtiesSaved).isNotNull();
        Assertions.assertThat(specialtiesSaved).isNotEmpty();

    }

    @Test
    @DisplayName("Find Specialty By SpecialtyName when Successful")
    public void find_specialtyBySpecialtyName_whenSuccessful(){
        List<SpecialtyEntity> specialties = new ArrayList<>();
        for(Specialty s : Specialty.values()){
            specialties.add(createSpecialty(s));
        }

        List<SpecialtyEntity> specialtiesSaved = specialtyEntityRepository.saveAll(specialties);

        Optional<SpecialtyEntity> specialty = specialtyEntityRepository.findBySpecialtyName(Specialty.SPECIALTY_GENERAL_PRACTITIONER);

        Assertions.assertThat(specialty).isNotNull();
        Assertions.assertThat(specialty.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Find List of Specialties when Successful")
    public void find_ListOfSpecialtiesWhenSuccessful(){
        List<SpecialtyEntity> specialties = new ArrayList<>();
        for(Specialty s : Specialty.values()){
            specialties.add(createSpecialty(s));
        }

        List<SpecialtyEntity> specialtiesSaved = specialtyEntityRepository.saveAll(specialties);

        List<SpecialtyEntity> specialtiesList = specialtyEntityRepository.findAll();

        Assertions.assertThat(specialtiesList).isNotNull();
        Assertions.assertThat(specialtiesList).isNotEmpty();

    }


}