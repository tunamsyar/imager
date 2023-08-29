package com.demo.imager.validators;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class FileExtensionValidator {
  String[] contentTypes = { "image/gif", "image/jpeg", "image/png" };

  public Boolean validateContentType(String contentType) {
    return Arrays.asList(contentTypes).contains(contentType);
  }
}
