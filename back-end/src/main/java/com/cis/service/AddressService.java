package com.cis.service;

import com.cis.exceptions.InconsistentDataException;
import com.cis.exceptions.ResourceNotFoundException;
import com.cis.model.Address;
import com.cis.repository.AddressRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Service
public class AddressService {
  private AddressRepository repository;

  public AddressService(AddressRepository repository) {
    this.repository = repository;
  }

  public List<Address> listAll() {
    return repository.findAll();
  }

  public Address findByIdOrThrowResourceNotFoundException(UUID id) {
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

  public Address findByStreet(String street) {
    return repository.findByStreetIgnoreCase(street);
  }

  //  public Address findByStreetContaining(String streetPattern) {
  //    return repository.findByStreetContaining(streetPattern);
  //  }

  @Transactional
  public Address save(Address address) throws Exception {

    String street = address.getStreet().toLowerCase(Locale.ROOT);
    Address addressByStreet = repository.findByStreetIgnoreCase(street);

    Address addressByCep = repository.findByCep(address.getCep());

    Address addressToBeSaved =
        CepService.convertCepToAddress(CepService.formatCep(address.getCep()));

    if (Objects.equals(addressToBeSaved.getNeighborhood(), "")
        && Objects.equals(addressToBeSaved.getStreet(), "")) {
      if (addressByStreet == null) {
        return repository.save(
            Address.builder()
                .id(UUID.randomUUID())
                .cep(address.getCep())
                .street(address.getStreet())
                .city(address.getCity())
                .uf(address.getUf())
                .neighborhood(address.getNeighborhood())
                .build());

      } else {
        return addressByStreet;
      }
    } else {
      if (addressByCep != null) {
        return addressByCep;
      } else {
        if (!(Objects.equals(address.getCep(), addressToBeSaved.getCep())
            & Objects.equals(address.getStreet(), addressToBeSaved.getStreet())
            & Objects.equals(address.getCity(), addressToBeSaved.getCity())
            & Objects.equals(address.getUf(), addressToBeSaved.getUf())
            & Objects.equals(address.getNeighborhood(), addressToBeSaved.getNeighborhood()))) {
          throw new InconsistentDataException(
              "Os dados de endereço não batem. Por favor, tente novamente.");
        } else {
          return repository.save(address);
        }
      }
    }
  }

  public void saveAll(List<Address> addresses) {
    repository.saveAll(addresses);
  }

  public void delete(UUID id) {
    repository.delete(findByIdOrThrowResourceNotFoundException(id));
  }

  public void deleteAll() {
    repository.deleteAll();
  }

  public void update(Address address) throws Exception {

    Address findAddress = repository.findByCep(address.getCep());

    if (findAddress == null) {
      throw new ResourceNotFoundException("Cep não encontrado, por favor tente novamente.");
    } else {
      Address addressToBeSaved =
          CepService.convertCepToAddress(CepService.formatCep(address.getCep()));

      if (!(Objects.equals(address.getCep(), addressToBeSaved.getCep())
          & Objects.equals(address.getStreet(), addressToBeSaved.getStreet())
          & Objects.equals(address.getCity(), addressToBeSaved.getCity())
          & Objects.equals(address.getUf(), addressToBeSaved.getUf())
          & Objects.equals(address.getNeighborhood(), addressToBeSaved.getNeighborhood()))) {
        throw new InconsistentDataException(
            "Os dados de endereço não batem. Por favor, tente novamente.");
      } else {
        repository.save(address);
      }
    }
  }
}
