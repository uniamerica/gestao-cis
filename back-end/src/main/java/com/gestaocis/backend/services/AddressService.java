package com.gestaocis.backend.services;

import com.gestaocis.backend.exceptions.ResourceAlreadyExistsException;
import com.gestaocis.backend.exceptions.ResourceNotFoundException;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddressService {

  private AddressRepository repository;

  public AddressService(AddressRepository repository) {
    this.repository = repository;
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
                    "Endereço não encontrado. Por favor, tente novamente."));
  }

  public Address findByCep(String cep) {
    if (repository.findByCep(cep) == null) {
      throw new ResourceNotFoundException("Endereço não encontrado. Por favor, tente novamente.");
    } else {
      return repository.findByCep(cep);
    }
  }

  @Transactional
  public void save(Address address) throws Exception {
    Address findAddress = repository.findByCep(address.getCep());
    if (findAddress != null) {
      throw new ResourceAlreadyExistsException("Endereço já existente.");
    } else {
      repository.save(address);
    }
  }

  public void delete(Long id) {
    repository.delete(findByIdOrThrowResourceNotFoundException(id));
  }

  public void replace(String cep) throws Exception {
    Address findAddress = repository.findByCep(cep);
    if (findAddress == null) {
      throw new ResourceNotFoundException("Cep não encontrado, por favor tente novamente.");
    } else {
      repository.save(CepService.convertCepToAddress(cep));
    }
  }
}
