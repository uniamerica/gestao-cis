package com.gestaocis.backend.services;

import com.gestaocis.backend.DTOs.ProfessionalDTOs.NewProfessionalRequestDTO;
import com.gestaocis.backend.DTOs.ProfessionalDTOs.ProfessionalResponseDTO;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfessionalService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleEntityRepository roleEntityRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;

    public ProfessionalService() {
    }

    public ProfessionalResponseDTO save(NewProfessionalRequestDTO professional) throws Exception {
        RoleEntity role = roleEntityRepository
                .findByRoleName(Role.ROLE_PROFESSIONAL)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role not found"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Address address = addressService.save(CepService.convertCepToAddress(CepService.formatCep(professional.getCep())));

            User professionalUser = User
                    .builder()
                    .uuid(new UUID(1L, 2L))
                    .cpf(professional.getCpf())
                    .rg(professional.getRg())
                    .email(professional.getEmail())
                    .phone(professional.getPhone())
                    .fullName(professional.getFullName())
                    .mothersName(professional.getMothersName())
                    .birthdate(sdf.parse(professional.getBirthdate()).toInstant())
                    .sex(professional.getSex())
                    .placeOfBirth(professional.getPlaceOfBirth())
                    .addressCountry(professional.getAddressCountry())
                    .addressLine2(professional.getAddressLine2())
                    .professionalDocument(professional.getProfessionalDocument())
                    .password(new BCryptPasswordEncoder().encode(professional.getPassword()))
                    .role(role)
                    .address(address)
                    .active(true)
                    .build();

            User professionalSaved = userRepository.save(professionalUser);
            return new ProfessionalResponseDTO(professionalSaved);
        } catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    public List<ProfessionalResponseDTO> findAll() {
        try {
            RoleEntity role = this.roleEntityRepository
                    .findByRoleName(Role.ROLE_PROFESSIONAL)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role Not Found"));
            List<User> patients = this.userRepository.findByRole(role);
            return patients.stream().map(ProfessionalResponseDTO::new).collect(Collectors.toList());
        } catch (Exception exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }
    
    public ProfessionalResponseDTO findByUUID(UUID uuid) {
        try {
            User professional = this.userRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Professional not Found, please check your uuid again"));
            return new ProfessionalResponseDTO(professional);
        } catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    public ProfessionalResponseDTO update (UUID uuid, NewProfessionalRequestDTO professional){
        try{
            User professionalFound = this.userRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Professional not Found, please check your uuid again"));
            Address address = addressService.save(CepService.convertCepToAddress(CepService.formatCep(professional.getCep())));
            professionalFound.setFullName(professional.getFullName());
            professionalFound.setEmail(professional.getEmail());
            professionalFound.setPhone(professional.getPhone());
            professionalFound.setAddress(address);
            professionalFound.setAddressCountry(professional.getAddressCountry());
            professionalFound.setAddressLine2(professional.getAddressLine2());

            return new ProfessionalResponseDTO(this.userRepository.save(professionalFound));
        } catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }
    public boolean delete(UUID uuid){
        try{
            User professional = this.userRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Professional not Found, please check your uuid again"));
            this.userRepository.delete(professional);

            if(userRepository.findByUuid(uuid).isEmpty()) {
                return true;
            }else{
                return false;
            }

        } catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }
    
}
