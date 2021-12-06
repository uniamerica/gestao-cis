package com.cis.utils;

import java.io.BufferedReader;
import java.io.IOException;

public class JsonToStringUtil {

  public static String convertJsonToString(BufferedReader bufferedReader) throws IOException {
    String response, jsonToString = "";

    while ((response = bufferedReader.readLine()) != null) {
      jsonToString += response;
    }

    return jsonToString;
  }
}
