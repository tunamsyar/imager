package com.demo.imager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.CommandLineRunner;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.demo.imager.services.FilesStorageService;

import jakarta.annotation.Resource;

@SpringBootApplication
@ComponentScan(basePackages = "com.demo.imager.*")

public class ImagerApplication implements CommandLineRunner {
  @Resource
  FilesStorageService storageService;

  public static void main(String[] args) {
    SpringApplication.run(ImagerApplication.class, args);
  }

  public void run(String... arg) throws Exception {
    storageService.init();
  }
}
