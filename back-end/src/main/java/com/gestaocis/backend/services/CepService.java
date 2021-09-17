package com.gestaocis.backend.services;

import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.utils.JsonToStringUtil;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class CepService {

  static String webService = "http://viacep.com.br/ws/";
  static int successStatusCode = 200;

  public static Address findAddressByCep(String cep) throws Exception {
    String fullUrl = webService + cep + "/json";

    try {
      URL url = new URL(fullUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      if (connection.getResponseCode() != successStatusCode) {
        throw new RuntimeException("HTTP error code: " + connection.getResponseCode());
      }

      BufferedReader response =
          new BufferedReader(new InputStreamReader((connection.getInputStream())));
      String jsonToString = JsonToStringUtil.convertJsonToString(response);

      Gson gson = new Gson();

      return gson.fromJson(jsonToString, Address.class);
    } catch (Exception exception) {
      throw new Exception("Erro: " + exception);
    }
  }
}
