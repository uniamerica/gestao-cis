package com.cis.service;

import com.cis.exceptions.BadRequestException;
import com.cis.model.HealthProfessional;
import com.cis.model.Specialty;
import com.cis.model.dto.HeathProfessionalDTO.HealthProfessionalCreationDTO;
import com.cis.model.dto.HeathProfessionalDTO.HealthProfessionalResponseDTO;
import com.cis.repository.HealthProfessionalRepository;
import com.cis.repository.SpecialtyRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HealthProfessionalService implements UserDetailsService {

  private final HealthProfessionalRepository healthProfessionalRepository;
  private final SpecialtyRepository specialtyRepository;

  public HealthProfessionalService(HealthProfessionalRepository healthProfessionalRepository, SpecialtyRepository specialtyRepository) {
    this.healthProfessionalRepository = healthProfessionalRepository;
    this.specialtyRepository = specialtyRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return this.healthProfessionalRepository
        .findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Invalid Email or Password"));
  }

  public List<HealthProfessionalResponseDTO> listAll(){
    List<HealthProfessional> all = healthProfessionalRepository.findAll();
    return all.stream().map(HealthProfessionalResponseDTO::new).collect(Collectors.toList());
  }

  public HealthProfessionalResponseDTO findById(UUID id) {
    HealthProfessional healthProfessional =
        healthProfessionalRepository
            .findById(id)
            .orElseThrow(() -> new BadRequestException("User Not Found"));
    return new HealthProfessionalResponseDTO(healthProfessional);
  }

  @Transactional
  public HealthProfessionalResponseDTO create(HealthProfessionalCreationDTO entity) {

    Optional<HealthProfessional> emailAlreadyExists =
        healthProfessionalRepository.findByEmail(entity.getEmail());

    if (emailAlreadyExists.isPresent()) {
      throw new BadRequestException("Email Already Exists");
    }

    Specialty specialty = specialtyRepository.findById(entity.getSpecialtyId()).orElseThrow(() -> new BadRequestException("Specialty Not Found"));

    HealthProfessional professionalToBeSaved = new HealthProfessional();

    professionalToBeSaved.setProfessional_id(UUID.randomUUID());
    professionalToBeSaved.setName(entity.getName());
    professionalToBeSaved.setPhone(entity.getPhone());
    professionalToBeSaved.setProfessionalDocument(entity.getProfessionalDocument());
    professionalToBeSaved.setEmail(entity.getEmail());
    professionalToBeSaved.setRole("ROLE_PROFESSIONAL");
    professionalToBeSaved.setSpecialty(specialty);
    professionalToBeSaved.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
    professionalToBeSaved.setActive(true);

    HealthProfessional save = healthProfessionalRepository.save(professionalToBeSaved);

    return new HealthProfessionalResponseDTO(save);
  }

  public HealthProfessionalResponseDTO update(UUID id, HealthProfessionalCreationDTO entity) {
    HealthProfessional healthProfessional =
        healthProfessionalRepository
            .findById(id)
            .orElseThrow(() -> new BadRequestException("User Not Found"));

    healthProfessional.setName(entity.getName());
    healthProfessional.setPhone(entity.getPhone());
    healthProfessional.setProfessionalDocument(entity.getProfessionalDocument());

    HealthProfessional save = healthProfessionalRepository.save(healthProfessional);

    return new HealthProfessionalResponseDTO(save);
  }

  public void delete(UUID id) {
    HealthProfessional healthProfessional =
        healthProfessionalRepository
            .findById(id)
            .orElseThrow(() -> new BadRequestException("User Not Found"));
    healthProfessionalRepository.delete(healthProfessional);
  }

  public void deleteAll() {
    healthProfessionalRepository.deleteAll();
  }
}
