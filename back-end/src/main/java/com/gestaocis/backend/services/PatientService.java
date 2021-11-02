package com.gestaocis.backend.services;

import com.gestaocis.backend.DTOs.PatientDTOs.NewPatientRequestDTO;
import com.gestaocis.backend.DTOs.PatientDTOs.PatientResponseDTO;
import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.exceptions.BadRequestException;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.HealthInsuranceRepository;
import com.gestaocis.backend.repositories.RoleEntityRepository;
import com.gestaocis.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {
  @Autowired private UserRepository userRepository;

  @Autowired private RoleEntityRepository roleEntityRepository;

  @Autowired private AddressService addressService;

  @Autowired private HealthInsuranceRepository healthInsuranceRepository;

  // SAVES NEW PATIENT
  public PatientResponseDTO save(NewPatientRequestDTO patient) throws Exception {

    RoleEntity role =
        roleEntityRepository
            .findByRoleName(Role.ROLE_PATIENT)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role not found"));

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    try {
      Address address =
          addressService.save(
              CepService.convertCepToAddress(CepService.formatCep(patient.getCep())));
      // HealthInsurance healthInsurance =
      // healthInsuranceRepository.findByInsuranceName(patient.getInsuranceName()).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Infelizmente o plano não cobre, sr."));

      User patientUser =
          User.builder()
              .uuid(UUID.randomUUID())
              .cpf(patient.getCpf())
              .rg(patient.getRg())
              .email(patient.getEmail())
              .phone(patient.getPhone())
              .fullName(patient.getFullName())
              .mothersName(patient.getMothersName())
              .birthdate(sdf.parse(patient.getBirthdate()).toInstant())
              .sex(patient.getSex())
              .placeOfBirth(patient.getPlaceOfBirth())
              .addressCountry(patient.getAddressCountry())
              .addressLine2(patient.getAddressLine2())
              // .healthInsurance(healthInsurance)
              .password(new BCryptPasswordEncoder().encode(patient.getPassword()))
              .role(role)
              .address(address)
              .active(true)
              .build();

      if (userRepository.findByCpfAndRole(patientUser.getCpf(), role).isPresent()) {
        throw new BadRequestException("Paciente já cadastrado.");
      } else {
        /*User patientSaved = patientRepository.save(patientUser);
        return new PatientResponseDTO(patientSaved);*/
        return new PatientResponseDTO(userRepository.save(patientUser));
      }

    } catch (Exception exception) {
      throw new BadRequestException(exception.getMessage());
    }
  }

  // FINDS ALL BY ROLE
  public List<PatientResponseDTO> findAll() {
    try {
      RoleEntity role =
          this.roleEntityRepository
              .findByRoleName(Role.ROLE_PATIENT)
              .orElseThrow(
                  () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role Not Found"));
      List<User> patients = this.userRepository.findByRole(role);
      return patients.stream().map(PatientResponseDTO::new).collect(Collectors.toList());
    } catch (Exception exception) {
      throw new BadRequestException(exception.getMessage());
    }
  }

  // FINDS BY UUID
  public PatientResponseDTO findByUUID(UUID uuid) {
    try {
      User patient =
          this.userRepository
              .findByUuid(uuid)
              .orElseThrow(
                  () ->
                      new ResponseStatusException(
                          HttpStatus.BAD_REQUEST, "Paciente não encontrado"));
      return new PatientResponseDTO(patient);
    } catch (Exception exception) {
      throw new BadRequestException(exception.getMessage());
    }
  }

  // FINDS BY EMAIL
  public PatientResponseDTO findByEmail(String email) {
    try {
      User patient =
          this.userRepository
              .findByEmail(email)
              .orElseThrow(
                  () ->
                      new ResponseStatusException(
                          HttpStatus.BAD_REQUEST,
                          "Paciente não encontrado, por favor tente novamente"));
      return new PatientResponseDTO(patient);
    } catch (Exception exception) {
      throw new BadRequestException(exception.getMessage());
    }
  }

  // FINDS ALL BY FULL NAME
  public List<PatientResponseDTO> findByFullName(String name) {
    try {
      List<User> patientsFound = this.userRepository.findByFullNameContainingIgnoreCase(name);
      return patientsFound.stream().map(PatientResponseDTO::new).collect(Collectors.toList());
    } catch (Exception exception) {
      throw new BadRequestException(exception.getMessage());
    }
  }

  // FINDS BY CPF
  public PatientResponseDTO findByCpf(String cpf) {
    try {
      User patient =
          this.userRepository
              .findByCpf(cpf)
              .orElseThrow(
                  () ->
                      new ResponseStatusException(
                          HttpStatus.BAD_REQUEST,
                          "Paciente não encontrado, por favor tente novamente"));
      return new PatientResponseDTO(patient);
    } catch (Exception exception) {
      throw new BadRequestException(exception.getMessage());
    }
  }

  // UPDATES (finding by UUID)
  public PatientResponseDTO update(UUID uuid, NewPatientRequestDTO patient) {
    try {
      User patientFound =
          this.userRepository
              .findByUuid(uuid)
              .orElseThrow(
                  () ->
                      new ResponseStatusException(
                          HttpStatus.BAD_REQUEST,
                          "Paciente não encontrado, por favor tente novamente"));
      Address address =
          addressService.save(
              CepService.convertCepToAddress(CepService.formatCep(patient.getCep())));
      patientFound.setFullName(patient.getFullName());
      patientFound.setEmail(patient.getEmail());
      patientFound.setPhone(patient.getPhone());
      patientFound.setAddress(address);
      patientFound.setAddressCountry(patient.getAddressCountry());
      patientFound.setAddressLine2(patient.getAddressLine2());
      // HealthInsurance healthInsurance = healthInsuranceRepository.save

      return new PatientResponseDTO(this.userRepository.save(patientFound));
    } catch (Exception exception) {
      throw new BadRequestException(exception.getMessage());
    }
  }

  // DELETE (finding by UUID)
  public boolean delete(UUID uuid) {
    try {
      User patient =
          this.userRepository
              .findByUuid(uuid)
              .orElseThrow(
                  () ->
                      new ResponseStatusException(
                          HttpStatus.BAD_REQUEST,
                          "Paciente não encontrado, por favor tente novamente"));
      this.userRepository.delete(patient);

      return userRepository.findByUuid(uuid).isEmpty();

    } catch (Exception exception) {
      throw new BadRequestException(exception.getMessage());
    }
  }
}
