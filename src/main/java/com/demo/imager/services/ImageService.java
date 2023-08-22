package com.demo.imager.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.demo.imager.repositories.ImageRepository;
import com.demo.imager.models.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class ImageService {
  private final ImageRepository imageRepository;

  @Autowired
  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  public Image uploadFile(MultipartFile file) throws IOException {
    String filename = file.getOriginalFilename();
    String contentType = file.getContentType();
    String uploadedAt = LocalDateTime.now().toString();

    Image image = new Image();
    image.setFilename(filename);
    image.setContentType(contentType);
    image.setUploadedAt(uploadedAt);
    imageRepository.save(image);

    return image;
  }
}
