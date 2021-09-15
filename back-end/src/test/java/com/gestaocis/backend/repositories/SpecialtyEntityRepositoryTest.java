package com.gestaocis.backend.repositories;

import com.gestaocis.backend.utils.enums.SpecialtyEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class SpecialtyEntityRepositoryTest {

    @Autowired
    private SpecialtyEntityRepository specialtyEntityRepository;

    private SpecialtyEntity createSpecialty(){
        return SpecialtyEntity
                .builder()
                .specialtyName("FISIOTERAPIA")
                .build();
    }

    @Test
    @DisplayName("Save Specialty when successful")
    void save_specialty_whenSuccessful(){
        SpecialtyEntity specialty = createSpecialty();
        SpecialtyEntity specialtySaved = this.specialtyEntityRepository.save(specialty);

        Assertions.assertThat(specialtySaved).isNotNull();
        Assertions.assertThat(specialtySaved.getSpecialtyName()).isEqualTo(specialty.getSpecialtyName());
    }

    @Test
    @DisplayName("Find List of Specialty when successful")
    void find_listOfSpecialty_WhenSuccessful(){
        SpecialtyEntity specialty = createSpecialty();
        SpecialtyEntity specialtySaved = this.specialtyEntityRepository.save(specialty);

        List<SpecialtyEntity> specialties = this.specialtyEntityRepository.findAll();

        Assertions.assertThat(specialties)
                .isNotNull()
                .isNotEmpty()
                .contains(specialtySaved);
    }

    @Test
    @DisplayName("Find by SpecialtyName when Successuful")
    void find_specialtyBySpecialtyName_whenSuccessful(){
        SpecialtyEntity specialty = createSpecialty();
        SpecialtyEntity specialtySaved = this.specialtyEntityRepository.save(specialty);

        Optional<SpecialtyEntity> specialtyFind = this.specialtyEntityRepository.findBySpecialtyNameIgnoreCase("fisioterapia");

        Assertions.assertThat(specialtyFind.get())
                .isNotNull()
                .isEqualTo(specialtySaved);
    }

    @Test
    @DisplayName("Update Specialty when Successful")
    void update_specialty_whenSuccessful(){
        SpecialtyEntity specialty = createSpecialty();
        SpecialtyEntity specialtyToCompare = createSpecialty();
        SpecialtyEntity specialtySaved = this.specialtyEntityRepository.save(specialty);

        specialtySaved.setSpecialtyName("MICROFISIOTERAPIA");
        SpecialtyEntity specialtyUpdated = this.specialtyEntityRepository.save(specialtySaved);

        Assertions.assertThat(specialtyUpdated.getId()).isEqualTo(specialtySaved.getId());
        Assertions.assertThat(specialtyUpdated.getSpecialtyName()).isNotEqualTo(specialtyToCompare.getSpecialtyName());
    }

    @Test
    @DisplayName("Delete Specialty when Successful")
    void delete_specialty_whenSuccessful(){
        SpecialtyEntity specialty = createSpecialty();
        SpecialtyEntity specialtySaved = this.specialtyEntityRepository.save(specialty);

        this.specialtyEntityRepository.deleteById(specialtySaved.getId());

        List<SpecialtyEntity> specialties = this.specialtyEntityRepository.findAll();

        Assertions.assertThat(specialties).doesNotContain(specialtySaved);
    }

}