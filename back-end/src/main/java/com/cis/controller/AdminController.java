package com.cis.controller;

import com.cis.config.jwtConfig.JwtUtil;
import com.cis.model.Admin;
import com.cis.model.dto.AdminDTO.AdminCreationDTO;
import com.cis.model.dto.AdminDTO.AdminReturnDTO;
import com.cis.model.dto.AdminDTO.AdminUpdateDTO;
import com.cis.model.dto.UserDTO.AuthenticationRequest;
import com.cis.model.dto.UserDTO.AuthenticationResponse;
import com.cis.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtTokenUtil;
  private AdminService service;

  public AdminController(
      AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, AdminService service) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenUtil = jwtTokenUtil;
    this.service = service;
  }

  // LIST
  @GetMapping
  public ResponseEntity<Page<Admin>> list(Pageable pageable) {
    return ResponseEntity.ok(service.listAll(pageable));
  }

  // SAVE
  @PostMapping
  public ResponseEntity<AdminReturnDTO> save(@RequestBody @Valid AdminCreationDTO admin) {
    return new ResponseEntity<>(service.save(admin), HttpStatus.CREATED);
  }

  // LOGIN
  @PostMapping(path = "/login")
  public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
      @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    try {

      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              authenticationRequest.getEmail(), authenticationRequest.getPassword()));

      final UserDetails user = service.loadUserByUsername(authenticationRequest.getEmail());

      Optional<? extends GrantedAuthority> optionalGrantedAuthority =
          user.getAuthorities().stream().findFirst();

      if (optionalGrantedAuthority.isEmpty()) {
        throw new Exception("Incorrect username or password");
      }

      if (!optionalGrantedAuthority.get().getAuthority().equals("ROLE_ADMIN")) {
        throw new Exception("Incorrect username or password");
      }

      final String jwt = jwtTokenUtil.generateToken(user);

      return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.CREATED);

    } catch (BadCredentialsException e) {
      throw new Exception("Incorrect username or password", e);
    }
  }

  // FIND BY ID
  @GetMapping("/{id}")
  public ResponseEntity<AdminReturnDTO> findById(@PathVariable("id") UUID id) {
    return ResponseEntity.ok(service.findByIdOrThrowError(id));
  }

  // EDIT
  @PutMapping("/{id}")
  public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody AdminUpdateDTO admin) {
    return new ResponseEntity<>(service.update(id, admin), HttpStatus.OK);
  }

  // DELETE
  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
    return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
  }
}
