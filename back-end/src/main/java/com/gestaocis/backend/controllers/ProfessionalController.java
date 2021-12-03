package com.gestaocis.backend.controllers;

import com.gestaocis.backend.DTOs.PatientDTOs.NewPatientRequestDTO;
import com.gestaocis.backend.DTOs.PatientDTOs.PatientResponseDTO;
import com.gestaocis.backend.DTOs.ProfessionalDTO.ProfessionalRequestDTO;
import com.gestaocis.backend.DTOs.ProfessionalDTO.ProfessionalResponseDTO;
import com.gestaocis.backend.config.jwt.AuthenticationRequest;
import com.gestaocis.backend.config.jwt.AuthenticationResponse;
import com.gestaocis.backend.config.jwt.JwtUtil;
import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.exceptions.BadRequestException;
import com.gestaocis.backend.services.ProfessionalService;
import com.gestaocis.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/professional")
public class ProfessionalController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfessionalService professionalService;

    @PostMapping(path = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);

        }
        final UserDetails user = userService.loadUserByUsername(authenticationRequest.getUsername());
        if(user.getAuthorities().contains(new SimpleGrantedAuthority(Role.ROLE_PROFESSIONAL.toString()))){
            final String jwt = jwtTokenUtil.generateToken(user);

            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        }else{
            throw new Exception("Incorrect username or password");
        }

    }

    @PostMapping
    public ResponseEntity<ProfessionalResponseDTO> create(@RequestBody ProfessionalRequestDTO requestDTO){
        try{
            ProfessionalResponseDTO professionalResponseDTO = professionalService.create(requestDTO);
            return new ResponseEntity<>(professionalResponseDTO, HttpStatus.CREATED);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
}
