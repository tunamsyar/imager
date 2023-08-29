package com.demo.imager.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import com.demo.imager.models.Image;
import com.demo.imager.repositories.ImageRepository;
import com.demo.imager.services.ImageService;
import com.demo.imager.validators.FileExtensionValidator;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
@Controller
public class ImageController {

  @Autowired
  FileExtensionValidator validator;

  @Autowired
  ImageService imageService;

  @Autowired
  ImageRepository imageRepository;

  // For gallery
  @GetMapping("/images")
  public ResponseEntity<Map<String, Object>> getAllImagesPage(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "3") int size) {

    List<Image> images = new ArrayList<Image>();
    Pageable paging = PageRequest.of(page, size);

    Page<Image> pageImages;
    pageImages = imageRepository.findAll(paging);

    images = pageImages.getContent();

    Map<String, Object> response = new HashMap<>();

    response.put("images", images);
    response.put("currentPage", pageImages.getNumber());
    response.put("totalItems", pageImages.getTotalElements());
    response.put("totalPages", pageImages.getTotalPages());

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/images/{id}")
  public ResponseEntity<Image> getImageById(@PathVariable("id") String id) {
    Optional<Image> imageData = imageService.findById(id);

    if (imageData.isPresent()) {
      return new ResponseEntity<>(imageData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/uploads/{id}")
  @ResponseBody
  public ResponseEntity<GridFsResource> getImage(@PathVariable String id) {
    GridFsResource file;
    try {
      file = imageService.downloadImage(id);
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // Upload
  @PostMapping("/upload")
  public ResponseEntity<Image> uploadFile(@RequestParam("file") MultipartFile file) {
    try {
      if (validator.validateContentType(file.getContentType())) {
        Image metadata = imageService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(metadata);
      } else {
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
      }
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // Delete
  @DeleteMapping("images/{id}")
  public ResponseEntity<HttpStatus> deleteImage(@PathVariable("id") String id) {
    try {
      imageRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
