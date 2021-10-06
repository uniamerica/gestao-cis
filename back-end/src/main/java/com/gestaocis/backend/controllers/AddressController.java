package com.gestaocis.backend.controllers;

import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/addresses")
public class AddressController {

  private final AddressService service;

  public AddressController(AddressService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Address>> list() {
    return ResponseEntity.ok(service.listAll());
  }

  @GetMapping(path = "/id/{id}")
  public ResponseEntity<Address> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(service.findByIdOrThrowResourceNotFoundException(id));
  }

  @GetMapping(path = "/cep/{cep}")
  public ResponseEntity<Address> findByCep(@PathVariable("cep") String cep) {
    return ResponseEntity.ok(service.findByCep(cep));
  }

  @PostMapping
  public ResponseEntity<Address> save(@RequestBody @Valid Address address) throws Exception {
    return new ResponseEntity<>(service.save(address), HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping
  public ResponseEntity<Void> replace(@RequestBody @Valid Address address) throws Exception {
    service.replace(address);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
