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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.UUID;

@Service
public class SecretaryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleEntityRepository roleEntityRepository;

    @Autowired
    private AddressService addressService;

    @Autowired private AddressRepository addressRepository;

    public SecretaryResponseDTO save(NewSecretaryRequestDTO secretary) throws Exception {

        RoleEntity role = roleEntityRepository
                .findByRoleName(Role.ROLE_SECRETARY)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role not found"));


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Address address = addressService.save(CepService.convertCepToAddress(CepService.formatCep(secretary.getCep())));

            User secretaryUser = User
                    .builder()
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

            User secretarySaved = userRepository.save(secretaryUser);
            return new SecretaryResponseDTO(secretarySaved);
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    public SecretaryResponseDTO findByUUID(UUID uuid){
        try{
            User secretary = this.userRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Secretary not Found, please check your uuid again"));
            return new SecretaryResponseDTO(secretary);
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

//    public SecretaryResponseDTO update (UUID uuid, NewSecretaryRequestDTO secretary){
//        try{
//            Address address = new Address(
//                    secretary.getCep(),
//                    secretary.getStreet(),
//                    secretary.getCity(),
//                    secretary.getUf(),
//                    secretary.getNeighborhood()
//            );
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        }catch (Exception exception){
//
//        }
//    }

}
