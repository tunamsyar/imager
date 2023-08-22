package com.demo.imager.repositories;

import com.demo.imager.models.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {

}
