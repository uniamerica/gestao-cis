package com.gestaocis.backend.resources;

import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/addresses")
public class AddressResource {

  private final AddressService service;

  public AddressResource(AddressService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Address>> list() {
    return ResponseEntity.ok(service.listAll());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Address> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(service.findByIdOrThrowResourceNotFoundException(id));
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody Address address) throws Exception {
    service.save(address);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping
  public ResponseEntity<Void> replace(@RequestBody String cep) throws Exception {
    service.replace(cep);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
