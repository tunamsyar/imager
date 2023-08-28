package com.demo.imager.services;

import java.io.IOException;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;

@Service
public class ImageStorageService {
  @Autowired
  private GridFsTemplate gridFsTemplate;

  @Autowired
  private GridFsOperations operations;

  public GridFsResource getImage(String id) throws IllegalStateException, IOException {
    GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
    operations.getResource(gridFSFile);

    return operations.getResource(gridFSFile);
  }

  public String addImage(String fileName, MultipartFile file) throws IOException {
    DBObject metaData = new BasicDBObject();
    metaData.put("filename", fileName);
    ObjectId id = gridFsTemplate.store(file.getInputStream(), file.getName(), file.getContentType(), metaData);
    return id.toString();
  }
}
