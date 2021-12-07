package com.cis.controller;

import com.cis.model.Address;
import com.cis.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/addresses")
public class AddressController {

  private final AddressService service;

  public AddressController(AddressService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Address>> list() {
    return ResponseEntity.ok(service.listAll());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Address> findById(@PathVariable("id") UUID id) {
    return ResponseEntity.ok(service.findByIdOrThrowResourceNotFoundException(id));
  }

  @GetMapping(path = "/search/")
  public ResponseEntity<Address> findByCep(@RequestParam String cep) {
    return ResponseEntity.ok(service.findByCep(cep));
  }

  @GetMapping(path = "/search/street/")
  public ResponseEntity<Address> findByStreet(@RequestParam String street) {
    return ResponseEntity.ok(service.findByStreet(street));
  }

  //  @GetMapping(path = "/search/street-containing/")
  //  public ResponseEntity<Address> findByStreetContaining(@RequestParam String pattern) {
  //    return ResponseEntity.ok(service.findByStreetContaining(pattern));
  //  }

  @PostMapping
  public ResponseEntity<Address> save(@RequestBody @Valid Address address) throws Exception {
    return new ResponseEntity<>(service.save(address), HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping
  public ResponseEntity<Void> replace(@RequestBody @Valid Address address) throws Exception {
    service.update(address);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
