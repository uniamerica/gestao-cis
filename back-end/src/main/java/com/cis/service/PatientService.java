package com.cis.service;

import com.cis.exceptions.BadRequestException;
import com.cis.model.Address;
import com.cis.model.Patient;
import com.cis.model.dto.PatientDTO.PatientCreationDTO;
import com.cis.model.dto.PatientDTO.PatientReturnDTO;
import com.cis.model.dto.PatientDTO.PatientUpdateDTO;
import com.cis.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService implements UserDetailsService {

  private PatientRepository repository;
  private AddressService addressService;

  public PatientService(PatientRepository repository, AddressService addressService) {
    this.repository = repository;
    this.addressService = addressService;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    return this.repository
        .findByEmailIgnoreCase(email)
        .orElseThrow(() -> new UsernameNotFoundException("Email ou senha inválidos."));
  }

  public Page<PatientReturnDTO> listAll(Pageable pageable) {
    Page<Patient> patients = repository.findAll(pageable);

    List<PatientReturnDTO> patientsList = new ArrayList<>();

    for (Patient patient : patients) {
      patientsList.add(new PatientReturnDTO(patient));
    }

    return new PageImpl<>(patientsList);
  }

  public PatientReturnDTO findByIdOrThrowError(UUID id) {

    Patient patient =
        repository
            .findById(id)
            .orElseThrow(() -> new BadRequestException("Paciente não encontrado."));
    return new PatientReturnDTO(patient);
  }

  public PatientReturnDTO findByEmailOrThrowError(String email) {

    Optional<Patient> foundPatient = repository.findByEmailIgnoreCase(email);

    if (foundPatient.isPresent()) {
      return new PatientReturnDTO(foundPatient.get());
    } else {
      throw new BadRequestException("Paciente não encontrado.");
    }
  }

  @Transactional
  public PatientReturnDTO save(PatientCreationDTO patientCreationDTO) throws Exception {

    Optional<Patient> findPatient = repository.findByEmailIgnoreCase(patientCreationDTO.getEmail());

    Address address =
        addressService.save(
            CepService.convertCepToAddress(CepService.formatCep(patientCreationDTO.getCep())));

    if (findPatient.isPresent()) {
      throw new BadRequestException("Paciente já cadastrado em nosso sistema.");
    } else {
      Patient patientToBeSaved = new Patient();

      patientToBeSaved.setPatientId(UUID.randomUUID());
      patientToBeSaved.setEmail(patientCreationDTO.getEmail());
      patientToBeSaved.setPassword(
          new BCryptPasswordEncoder().encode(patientCreationDTO.getPassword()));
      patientToBeSaved.setRole("ROLE_PATIENT");
      patientToBeSaved.setActive(true);
      patientToBeSaved.setName(patientCreationDTO.getName());
      patientToBeSaved.setRg(patientCreationDTO.getRg());
      patientToBeSaved.setCpf(patientCreationDTO.getCpf());
      patientToBeSaved.setDateOfBirth(patientCreationDTO.getDateOfBirth());
      patientToBeSaved.setPhone(patientCreationDTO.getPhone());
      patientToBeSaved.setMotherName(patientCreationDTO.getMotherName());
      patientToBeSaved.setGender(patientCreationDTO.getGender());
      patientToBeSaved.setAddressNumber(patientCreationDTO.getAddressNumber());
      patientToBeSaved.setAddressLine2(patientCreationDTO.getAddressLine2());
      patientToBeSaved.setAddress(address);

      Patient patient = repository.save(patientToBeSaved);

      return new PatientReturnDTO(patient);
    }
  }

  public String delete(UUID id) {
    repository.deleteById(id);
    return "Registro deletado com sucesso!";
  }

  public void deleteAll() {
    repository.deleteAll();
  }

  public String update(UUID id, PatientUpdateDTO patient) throws Exception {

    Patient savedPatient =
        repository
            .findById(id)
            .orElseThrow(() -> new BadRequestException("Paciente não encontrado."));

    Address address =
        addressService.save(CepService.convertCepToAddress(CepService.formatCep(patient.getCep())));

    savedPatient.setName(patient.getName());
    savedPatient.setEmail(patient.getEmail());
    savedPatient.setPhone(patient.getPhone());
    savedPatient.setGender(patient.getGender());
    savedPatient.setAddressNumber(patient.getAddressNumber());
    savedPatient.setAddressLine2(patient.getAddressLine2());
    savedPatient.setAddress(address);

    repository.save(savedPatient);
    return "Registro atualizado com sucesso!";
  }
}
