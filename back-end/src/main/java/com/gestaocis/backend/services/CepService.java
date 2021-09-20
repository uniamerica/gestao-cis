package com.gestaocis.backend.services;

import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.utils.JsonToStringUtil;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

@Service
public class CepService {

  static String webService = "http://viacep.com.br/ws/";
  static int successStatusCode = 200;

  public static Address convertCepToAddress(String cep) throws Exception {
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

      Address address = gson.fromJson(jsonToString, Address.class);

      return address;
    } catch (Exception exception) {
      throw new Exception("Erro: " + exception);
    }
  }

  public static String formatCep(String cep) {
    if (!Pattern.matches("[0-9]{5}-[0-9]{3}", cep)) {
      return addHifen(cep, '-', 5);
    } else {
      return cep;
    }
  }

  public static String addHifen(String cep, char c, int pos) {
    StringBuilder formattedCep = new StringBuilder(cep);
    formattedCep.insert(pos, c);
    return formattedCep.toString();
  }
}
