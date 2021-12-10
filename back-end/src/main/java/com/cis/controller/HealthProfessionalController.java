package com.cis.controller;

import com.cis.config.jwtConfig.JwtUtil;
import com.cis.exceptions.BadRequestException;
import com.cis.model.dto.HeathProfessionalDTO.HealthProfessionalCreationDTO;
import com.cis.model.dto.HeathProfessionalDTO.HealthProfessionalResponseDTO;
import com.cis.model.dto.UserDTO.AuthenticationRequest;
import com.cis.model.dto.UserDTO.AuthenticationResponse;
import com.cis.service.HealthProfessionalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/health-professionals")
public class HealthProfessionalController {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtTokenUtil;
  private final HealthProfessionalService healthProfessionalService;

  public HealthProfessionalController(
      AuthenticationManager authenticationManager,
      JwtUtil jwtTokenUtil,
      HealthProfessionalService healthProfessionalService) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenUtil = jwtTokenUtil;
    this.healthProfessionalService = healthProfessionalService;
  }

  @GetMapping
  public ResponseEntity<List<HealthProfessionalResponseDTO>> index(){
    try{
      return new ResponseEntity<>(healthProfessionalService.listAll(), HttpStatus.OK);
    }catch (Exception e){
      throw new BadRequestException(e.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity<HealthProfessionalResponseDTO> create(
      @RequestBody HealthProfessionalCreationDTO entity) {
    try {
      HealthProfessionalResponseDTO healthProfessionalResponseDTO =
          healthProfessionalService.create(entity);
      return new ResponseEntity<>(healthProfessionalResponseDTO, HttpStatus.CREATED);
    } catch (Exception e) {
      throw new BadRequestException(e.getMessage());
    }
  }

  @PostMapping(path = "/login")
  public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
      @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    try {

      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              authenticationRequest.getEmail(), authenticationRequest.getPassword()));

      final UserDetails user =
          healthProfessionalService.loadUserByUsername(authenticationRequest.getEmail());

      Optional<? extends GrantedAuthority> optionalGrantedAuthority =
          user.getAuthorities().stream().findFirst();

      if (optionalGrantedAuthority.isEmpty()) {
        throw new Exception("Incorrect username or password");
      }

      if (!optionalGrantedAuthority.get().getAuthority().equals("ROLE_PROFESSIONAL")) {
        throw new Exception("Incorrect username or password");
      }

      final String jwt = jwtTokenUtil.generateToken(user);

      return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.CREATED);

    } catch (BadCredentialsException e) {
      throw new Exception("Incorrect username or password", e);
    }
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<HealthProfessionalResponseDTO> show(@PathVariable UUID id) {
    try {
      HealthProfessionalResponseDTO healthProfessionalResponseDTO =
          healthProfessionalService.findById(id);
      return new ResponseEntity<>(healthProfessionalResponseDTO, HttpStatus.OK);
    } catch (Exception e) {
      throw new BadRequestException(e.getMessage());
    }
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<HealthProfessionalResponseDTO> update(
      @PathVariable UUID id, @RequestBody HealthProfessionalCreationDTO entity) {
    try {
      HealthProfessionalResponseDTO healthProfessionalResponseDTO =
          healthProfessionalService.update(id, entity);
      return new ResponseEntity<>(healthProfessionalResponseDTO, HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      throw new BadRequestException(e.getMessage());
    }
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<String> delete(@PathVariable UUID id) {
    try {
      healthProfessionalService.delete(id);
      return new ResponseEntity<>("Delete with success", HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      throw new BadRequestException(e.getMessage());
    }
  }
}
