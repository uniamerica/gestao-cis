package com.cis.util;

import com.cis.model.Address;
import com.cis.service.CepService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AddressCreator {

  public static Address createAddress(int option) throws Exception {
    switch (option) {
      case 1:
        Address address = CepService.convertCepToAddress("85857600");
        address.setId(UUID.fromString("9171004e-fb07-4cb1-bbbd-6010418481d5"));
        return address;

      case 2:
        Address address1 = CepService.convertCepToAddress("85851-010");
        address1.setId(UUID.fromString("9171004e-fb07-4cb1-bbbd-6010418481d6"));
        return address1;

      case 3:
        Address address2 = CepService.convertCepToAddress("85960000");
        address2.setId(UUID.fromString("9171004e-fb07-4cb1-bbbd-6010418481d7"));
        return address2;

      default:
        Address address3 = CepService.convertCepToAddress("85851000");
        address3.setId(UUID.fromString("9171004e-fb07-4cb1-bbbd-6010418481d8"));
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
    Address address = createAddressNoId(1);

    address.setCep("85851-010");

    Address updatedAddress = createAddressNoId(2);

    address.setCity(updatedAddress.getCity());
    address.setNeighborhood(updatedAddress.getNeighborhood());
    address.setStreet(updatedAddress.getStreet());

    return address;
  }

  public static List<Address> createAddressList() throws Exception {
    String[] ceps = {"08090-284", "04849529", "04843-425", "08226021", "04180-112"};

    List<Address> addressesToBeSaved = new ArrayList<>();

    int i = 1;

    for (String cep : ceps) {
      Address address = CepService.convertCepToAddress(cep);
      address.setId(UUID.fromString("bd213758-05f8-43f8-8732-471b7310830" + i));
      addressesToBeSaved.add(address);
      i += 1;
    }

    return addressesToBeSaved;
  }
}
