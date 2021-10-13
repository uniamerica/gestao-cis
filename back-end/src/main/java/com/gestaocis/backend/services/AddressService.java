package com.gestaocis.backend.services;

import com.gestaocis.backend.exceptions.InconsistentDataException;
import com.gestaocis.backend.exceptions.ResourceAlreadyExistsException;
import com.gestaocis.backend.exceptions.ResourceNotFoundException;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

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
    return repository.findByCep(CepService.formatCep(cep));
  }

  @Transactional
  public Address save(Address address) throws Exception {
    Address findAddress = repository.findByCep(address.getCep());
    if (findAddress != null) {
      throw new ResourceAlreadyExistsException("Endereço já existente.");
    } else {
      Address address1 = CepService.convertCepToAddress(CepService.formatCep(address.getCep()));
      if (!(Objects.equals(address.getCep(), address1.getCep())
          & Objects.equals(address.getStreet(), address1.getStreet())
          & Objects.equals(address.getCity(), address1.getCity())
          & Objects.equals(address.getUf(), address1.getUf())
          & Objects.equals(address.getNeighborhood(), address1.getNeighborhood()))) {
        throw new InconsistentDataException(
            "Os dados de endereço não batem. Por favor, tente novamente.");
      } else {
        return repository.save(address);
      }
    }
  }

  public void delete(Long id) {
    repository.delete(findByIdOrThrowResourceNotFoundException(id));
  }

  public void replace(Address address) throws Exception {
    Address findAddress = repository.findByCep(address.getCep());
    if (findAddress == null) {
      throw new ResourceNotFoundException("Cep não encontrado, por favor tente novamente.");
    } else {
      repository.save(address);
    }
  }
}
