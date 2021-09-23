package com.gestaocis.backend.resources;

import com.gestaocis.backend.services.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AddressResourceTest {

  @InjectMocks private AddressResource resource;

  @Mock private AddressService service;

  @Test
  void name() {}
}
