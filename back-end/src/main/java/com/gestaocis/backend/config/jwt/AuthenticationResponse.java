package com.gestaocis.backend.config.jwt;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

    private final String jwt;
}
