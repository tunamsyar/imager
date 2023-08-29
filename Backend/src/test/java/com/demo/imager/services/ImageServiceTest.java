package com.demo.imager.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.demo.imager.models.Image;
import com.demo.imager.repositories.ImageRepository;
import com.demo.imager.services.ImageService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ImageServiceTest {

  @Mock
  private ImageRepository imageRepository;

  @Mock
  private ImageStorageService imageStorageService;

  private ImageService imageService;

  @BeforeEach
  public void setUp() {
    imageService = new ImageService();
    imageService.imageStorageService = imageStorageService;
    imageService.imageRepository = imageRepository;
  }

  @Test
  public void testUploadImage() throws IOException {
    MultipartFile mockFile = new MockMultipartFile("test.jpg", "test.jpg",
        "image/jpeg", new byte[0]);

    when(imageRepository.save(any(Image.class))).thenAnswer(invocation -> invocation.getArgument(0));

    String fileId = new ObjectId().toString();
    when(imageStorageService.addImage(any(String.class),
        any(MultipartFile.class))).thenReturn(fileId);

    Image uploadedImage = imageService.uploadImage(mockFile);

    String url = "http://localhost:8080/api/uploads/" + fileId;
    assertEquals("test.jpg", uploadedImage.getFilename());
    assertEquals("image/jpeg", uploadedImage.getContentType());
    assertEquals(url, uploadedImage.getUrl());
  }

  @Test
  public void testGetAllImages() throws IOException {
    String dateTime = LocalDateTime.now().toString();
    List<Image> mockImageList = new ArrayList<>();

    Image image1 = new Image();
    image1.setId("1");
    image1.setFilename("image1.jpg");
    image1.setContentType("image/jpeg");
    image1.setUploadedAt(dateTime);
    image1.setUrl("http://localhost:8080/api/uploads/1");

    Image image2 = new Image();
    image2.setId("2");
    image2.setFilename("image1.jpg");
    image2.setContentType("image/jpeg");
    image2.setUploadedAt(dateTime);
    image2.setUrl("http://localhost:8080/api/uploads/2");

    mockImageList.add(image1);
    mockImageList.add(image2);

    when(imageRepository.findAll()).thenReturn(mockImageList);

    List<Image> images = imageService.getAllImages();

    assertEquals(2, images.size());
  }
}
