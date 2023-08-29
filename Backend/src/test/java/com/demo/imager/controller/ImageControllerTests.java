// package com.demo.imager.controller;

// import static org.mockito.Mockito.when;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import java.io.File;
// import java.io.FileInputStream;
// import java.time.LocalDateTime;

// import org.bson.types.ObjectId;
// import org.junit.jupiter.api.Test;

// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.beans.factory.annotation.Autowired;
// import
// org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.http.MediaType;
// import org.springframework.mock.web.MockMultipartFile;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.web.WebAppConfiguration;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.RequestBuilder;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.context.WebApplicationContext;
// import org.springframework.web.multipart.MultipartFile;

// import com.demo.imager.models.Image;
// import com.demo.imager.repositories.ImageRepository;
// import com.demo.imager.services.ImageService;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @WebMvcTest(controllers = ImageControllerTests.class)
// @AutoConfigureMockMvc(addFilters = false)
// @WebAppConfiguration(value = "")
// @ComponentScan(basePackages = "com.demo.imager.controller")
// @ContextConfiguration("file:src/main/resources/mongoConfig.xml")
// public class ImageControllerTests {

// @Autowired
// private MockMvc mockMvc;

// @Autowired
// private WebApplicationContext webApplicationContext;

// @MockBean
// private ImageRepository imageRepository;

// @Mock
// ImageService imageService;

// @Test
// public void shouldCreateImage() throws Exception {

// this.mockMvc =
// MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

// Image image = new Image();
// image.setId("1");
// image.setContentType("image/jpeg");
// image.setFilename("test-image.jpg");
// image.setUploadedAt(LocalDateTime.now().toString());
// image.setUrl("http://localhost:8080/uploads/test-image.jpg");

// when(imageService.uploadImage(Mockito.any(MultipartFile.class))).thenReturn(image);
// ClassLoader classLoader = getClass().getClassLoader();
// File file = new File(classLoader.getResource("test-image.jpg").getFile());

// FileInputStream input = new FileInputStream(file);
// MockMultipartFile multipartFile = new MockMultipartFile("file",
// "test-image.jpg", MediaType.IMAGE_JPEG_VALUE, input);

// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .multipart("/api/upload")
// .file(multipartFile);

// MvcResult result = mockMvc.perform(requestBuilder)
// .andExpect(status().isCreated())
// .andReturn();
// }

// @Test
// public void getImages() throws Exception {
// mockMvc.perform(MockMvcRequestBuilders.get("/api/images")).andExpect(status().isOk());
// }

// @Test
// public void getImageById() throws Exception {
// ObjectId id = new ObjectId();
// Image image = new Image();
// image.setId(id.toHexString());
// imageRepository.save(image);

// mockMvc.perform(MockMvcRequestBuilders.get("/api/images/{id}", id.toString())
// .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
// .andExpect(status().isOk());
// }

// public static String asJsonString(final Object obj) {
// try {
// return new ObjectMapper().writeValueAsString(obj);
// } catch (Exception e) {
// throw new RuntimeException(e);
// }
// }

// }
