package com.gestaocis.backend.services;

import com.gestaocis.backend.DTOs.SecretaryDTOs.NewSecretaryRequestDTO;
import com.gestaocis.backend.DTOs.SecretaryDTOs.SecretaryResponseDTO;
import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.exceptions.BadRequestException;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.AddressRepository;
import com.gestaocis.backend.repositories.RoleEntityRepository;
import com.gestaocis.backend.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SecretaryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleEntityRepository roleEntityRepository;

    @Autowired
    private AddressService addressService;

    @Autowired private AddressRepository addressRepository;

    // SAVE NEW SECRETARY
    public SecretaryResponseDTO save(NewSecretaryRequestDTO secretary) throws Exception {

        RoleEntity role = roleEntityRepository
                .findByRoleName(Role.ROLE_SECRETARY)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role not found"));


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Address address = addressService.save(CepService.convertCepToAddress(CepService.formatCep(secretary.getCep())));
            User secretaryUser = User
                    .builder()
                    .uuid(UUID.randomUUID())
                    .cpf(secretary.getCpf())
                    .rg(secretary.getRg())
                    .email(secretary.getEmail())
                    .phone(secretary.getPhone())
                    .fullName(secretary.getFullName())
                    .birthdate(sdf.parse(secretary.getBirthdate()).toInstant())
                    .sex(secretary.getSex())
                    .addressLine2(secretary.getAddressLine2())
                    .addressCountry(secretary.getAddressCountry())
                    .password(new BCryptPasswordEncoder().encode(secretary.getPassword()))
                    .role(role)
                    .address(address)
                    .active(true)
                    .build();


            return new SecretaryResponseDTO(userRepository.save(secretaryUser));
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    // FIND BY UUID
    public SecretaryResponseDTO findByUUID(UUID uuid){
        try{
            User secretary = this.userRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Secretary not Found, please check your uuid again"));
            return new SecretaryResponseDTO(secretary);
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    // FIND BY EMAIL
    public SecretaryResponseDTO findByEmail(String email){
        try{
            User secretary = this.userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Secretary not Found, please check your uuid again"));
            return new SecretaryResponseDTO(secretary);
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    // FIND ALL BY FULL NAME
    public List<SecretaryResponseDTO> findByFullName(String name){
        try{
            List<User> secretariesFound = this.userRepository.findByFullNameContainingIgnoreCase(name);
            return secretariesFound.stream().map(SecretaryResponseDTO::new).collect(Collectors.toList());
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    // FIND ALL BY ROLE
    public List<SecretaryResponseDTO> findByRole(){
        try{
            RoleEntity role = this.roleEntityRepository
                    .findByRoleName(Role.ROLE_SECRETARY)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role Not Found"));
            List<User> secretaries = this.userRepository.findByRole(role);
            return secretaries.stream().map(SecretaryResponseDTO::new).collect(Collectors.toList());
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    // FIND BY CPF
    public SecretaryResponseDTO findByCpf(String cpf){
        try{
            User secretary = this.userRepository.findByCpf(cpf)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Secretary not Found, please check your uuid again"));
            return new SecretaryResponseDTO(secretary);
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    // FIND BY UUID AND UPDATE SECRETARY
    public SecretaryResponseDTO update (UUID uuid, NewSecretaryRequestDTO secretary){
        try{
            User secretaryFound = this.userRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Secretary not Found, please check your uuid again"));
            Address address = addressService.save(CepService.convertCepToAddress(CepService.formatCep(secretary.getCep())));
            secretaryFound.setFullName(secretary.getFullName());
            secretaryFound.setEmail(secretary.getEmail());
            secretaryFound.setPhone(secretary.getPhone());
            secretaryFound.setAddress(address);
            secretaryFound.setAddressCountry(secretary.getAddressCountry());
            secretaryFound.setAddressLine2(secretary.getAddressLine2());

            return new SecretaryResponseDTO(this.userRepository.save(secretaryFound));
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    // FIND BY UUID AND DELETE SECRETARY
    public boolean delete(UUID uuid){
        try{
            User secretary = this.userRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Secretary not Found, please check your uuid again"));
            this.userRepository.delete(secretary);

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
