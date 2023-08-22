package com.demo.imager.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.demo.imager.models.Image;
import com.demo.imager.services.ImageService;

@RestController
@RequestMapping("/api/images/")
public class ImageController {

  private final ImageService imageService;

  @Autowired
  public ImageController(ImageService imageService) {
    this.imageService = imageService;
  }

  @PostMapping("/upload")
  public ResponseEntity<Image> uploadFile(@RequestParam("file") MultipartFile file) {
    try {
      Image metadata = imageService.uploadFile(file);
      return ResponseEntity.status(HttpStatus.CREATED).body(metadata);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
