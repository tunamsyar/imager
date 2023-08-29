package com.demo.imager.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "images")
public class Image {

  @Id
  String id;
  String filename;
  String contentType;
  String uploadedAt;
  String url;
}
