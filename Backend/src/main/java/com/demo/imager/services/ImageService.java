package com.demo.imager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import com.demo.imager.repositories.ImageRepository;
import com.demo.imager.models.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
  private final Path root = Paths.get("uploads");

  @Autowired
  ImageStorageService imageStorageService;

  @Autowired
  ImageRepository imageRepository;

  public Image uploadImage(MultipartFile file) throws IOException {
    String filename = file.getOriginalFilename();
    String contentType = file.getContentType();
    String uploadedAt = LocalDateTime.now().toString();

    Image image = new Image();
    image.setFilename(filename);
    image.setContentType(contentType);
    image.setUploadedAt(uploadedAt);

    String fileId = imageStorageService.addImage(filename, file);

    String baseUrl = "http://localhost:8080/api/";
    String relativePath = this.root.resolve(fileId).toString();
    image.setUrl(baseUrl + relativePath);

    imageRepository.save(image);
    return image;
  }

  public GridFsResource downloadImage(String Id) throws IOException {
    GridFsResource file = imageStorageService.getImage(Id);
    return file;
  }

  public List<Image> getAllImages() {
    return imageRepository.findAll();
  }

  public Optional<Image> findById(String id) {
    Optional<Image> image = imageRepository.findById(id);

    return image;
  }
}
