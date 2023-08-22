package com.demo.imager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.demo.imager")

public class ImagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ImagerApplication.class, args);
  }

}
