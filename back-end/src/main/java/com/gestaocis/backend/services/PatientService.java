package com.gestaocis.backend.services;


import com.gestaocis.backend.DTOs.PatientDTOs.NewPatientRequestDTO;
import com.gestaocis.backend.DTOs.PatientDTOs.PatientResponseDTO;
import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.exceptions.BadRequestException;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.models.HealthInsurance;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.*;
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
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleEntityRepository roleEntityRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private HealthInsuranceRepository healthInsuranceRepository;

    public PatientResponseDTO save(NewPatientRequestDTO patient) throws Exception {

        RoleEntity role = roleEntityRepository
                .findByRoleName(Role.ROLE_PATIENT)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role not found"));


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Address address = addressService.save(CepService.convertCepToAddress(CepService.formatCep(patient.getCep())));

            HealthInsurance healthInsurance = healthInsuranceRepository.findByInsuranceName(patient.getInsuranceName()).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Deu ruim"));

            User patientUser = User
                    .builder()
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
                    .healthInsurance(healthInsurance)
                    .password(new BCryptPasswordEncoder().encode(patient.getPassword()))
                    .role(role)
                    .address(address)
                    .active(true)
                    .build();

            User patientSaved = userRepository.save(patientUser);
            return new PatientResponseDTO(patientSaved);
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    public List<PatientResponseDTO> findAll(){
        try{
            RoleEntity role = this.roleEntityRepository
                    .findByRoleName(Role.ROLE_PATIENT)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role Not Found"));
            List<User> patients = this.userRepository.findByRole(role);
            return patients.stream().map(PatientResponseDTO::new).collect(Collectors.toList());
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    public PatientResponseDTO findByUUID(UUID uuid){
        try{
            User patient = this.userRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient not Found, please check your uuid again"));
            return new PatientResponseDTO(patient);
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    public PatientResponseDTO update (UUID uuid, NewPatientRequestDTO patient){
        try{
            User patientFound = this.userRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient not Found, please check your uuid again"));
            Address address = addressService.save(CepService.convertCepToAddress(CepService.formatCep(patient.getCep())));
            patientFound.setFullName(patient.getFullName());
            patientFound.setEmail(patient.getEmail());
            patientFound.setPhone(patient.getPhone());
            patientFound.setAddress(address);
            patientFound.setAddressCountry(patient.getAddressCountry());
            patientFound.setAddressLine2(patient.getAddressLine2());

            return new PatientResponseDTO(this.userRepository.save(patientFound));
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    public boolean delete(UUID uuid){
        try{
            User patient = this.userRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient not Found, please check your uuid again"));
            this.userRepository.delete(patient);

            if(userRepository.findByUuid(uuid).isEmpty()) {
                return true;
            }else{
                return false;
            }

        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }
}
