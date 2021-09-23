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
        return CepService.convertCepToAddress("85857600");

      case 2:
        return CepService.convertCepToAddress("85851-010");

      case 3:
        return CepService.convertCepToAddress("85960000");

      default:
        return CepService.convertCepToAddress("85851000");
    }
  }

  public static List<Address> createAddressList() throws Exception {
    String[] ceps = {"85851010", "85851-210", "85851000", "85852-000", "85851110"};

    List<Address> addressesToBeSaved = new ArrayList<>();

    for (String cep : ceps) {
      addressesToBeSaved.add(CepService.convertCepToAddress(cep));
    }

    return addressesToBeSaved;
  }

  // **** Resource ****

  public static Address createAddressToBeSaved() throws Exception {
    return createAddress(1);
  }

  public static Address createValidAddress() throws Exception {
    createAddressToBeSaved();
    return repository.findByCep(createAddressToBeSaved().getCep());
  }

  public static Address createValidUpdatedAddress() throws Exception {

    Address addressToBeSaved = createAddress(1);

    Address savedAddress = repository.save(addressToBeSaved);

    savedAddress.setStreet("Rua Ipanema");
    savedAddress.setCity("Toledo");
    savedAddress.setNeighborhood("Conjunto Libra");

    return repository.save(savedAddress);
  }
}
