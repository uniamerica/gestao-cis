package com.gestaocis.backend.services;

import com.gestaocis.backend.exceptions.ResourceAlreadyExistsException;
import com.gestaocis.backend.exceptions.ResourceNotFoundException;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

  private final AddressRepository repository;
  private final CepService cepService;

  public AddressService(AddressRepository repository, CepService cepService) {
    this.repository = repository;
    this.cepService = cepService;
  }

  public List<Address> listAll() {
    return repository.findAll();
  }

  public Address findByIdOrThrowResourceNotFoundException(Long id) {
    return repository
        .findById(id)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    "Endereço não encontrato. Por favor, tente novamente."));
  }

  @Transactional
  public void save(String cep) throws Exception {
    Optional<Address> findAddress = repository.findByCep(cep);
    if (findAddress.isPresent()) {
      throw new ResourceAlreadyExistsException("Endereço já existente.");
    } else {
      repository.save(CepService.findAddressByCep(cep));
    }
  }

  public void delete(Long id) {
    repository.delete(findByIdOrThrowResourceNotFoundException(id));
  }

  public void replace(String cep) throws Exception {
    Optional<Address> findAddress = repository.findByCep(cep);
    if (findAddress.isEmpty()) {
      throw new ResourceNotFoundException("Cep não encontrado, por favor tente novamente.");
    } else {
      repository.save(CepService.findAddressByCep(cep));
    }
  }
}
