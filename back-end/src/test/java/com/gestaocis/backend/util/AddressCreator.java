package com.gestaocis.backend.util;

import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.repositories.AddressRepository;
import com.gestaocis.backend.services.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressCreator {

  private static AddressRepository repository;

  @Autowired
  private AddressCreator(AddressRepository repository) {
    AddressCreator.repository = repository;
  }

  // **** Repository ****

  public static Address createAddress(int option) throws Exception {
    switch (option) {
      case 1:
        Address address = CepService.convertCepToAddress("85857600");
        address.setId(1L);
        return address;

      case 2:
        Address address1 = CepService.convertCepToAddress("85851-010");
        address1.setId(2L);
        return address1;

      case 3:
        Address address2 = CepService.convertCepToAddress("85960000");
        address2.setId(3L);
        return address2;

      default:
        Address address3 = CepService.convertCepToAddress("85851000");
        address3.setId(4L);
        return address3;
    }
  }

  public static Address createAddressNoId(int option) throws Exception {
    switch (option) {
      case 1:
        return CepService.convertCepToAddress("85857600");

      case 2:
        return CepService.convertCepToAddress("85851-010");

      case 3:
        return CepService.convertCepToAddress("85960000");

      default:
        return CepService.convertCepToAddress("85851000");
    }
  }

  public static Address createUpdatedAddress() throws Exception {

    Address savedAddress = repository.save(AddressCreator.createAddressNoId(1));

    savedAddress.setStreet("Rua Ipanema");
    savedAddress.setCity("Toledo");
    savedAddress.setNeighborhood("Conjunto Libra");

    return repository.save(savedAddress);
  }

  public static List<Address> createAddressList() throws Exception {
    String[] ceps = {"85851010", "85851-210", "85851000", "85852-000", "85851110"};

    List<Address> addressesToBeSaved = new ArrayList<>();

    for (String cep : ceps) {
      addressesToBeSaved.add(CepService.convertCepToAddress(cep));
    }

    return addressesToBeSaved;
  }
}
