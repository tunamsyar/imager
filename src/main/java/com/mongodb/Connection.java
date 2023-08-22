package com.mongodb;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Connection {

  private static final String DATABASE_NAME = "local";
  private static final String CONNECTION_STRING = "mongodb://tun:password@0.0.0.0:8181/";

  private MongoClient mongoClient;
  private MongoDatabase database;

  public Connection() {
    // creates connnection
    mongoClient = MongoClients.create(CONNECTION_STRING);

    // gets database
    database = mongoClient.getDatabase(DATABASE_NAME);
  }

  public MongoClient getMongoClient() {
    return mongoClient;
  }

  public MongoDatabase getDatabase() {
    return database;
  }

  public void close() {
    if (mongoClient != null) {
      mongoClient.close();
    }
  }

  public static void main(String[] args) {
    Connection connection = new Connection();
    MongoDatabase database = connection.getDatabase();

    System.out.println("Connected to database: " + database.getName());

    connection.close();
  }
}
