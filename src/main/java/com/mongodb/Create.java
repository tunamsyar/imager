package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Create<T> {

  private final Connection connection;

  public Create(Connection connection) {
    this.connection = connection;
  }

  public void insertDocument(String collectionName, T data) {
    try {
      // Get a reference to the database
      MongoDatabase database = connection.getDatabase();

      // Get the specified collection
      MongoCollection<Document> collection = database.getCollection(collectionName);

      // Convert data to a Document and insert it into the collection
      if (data instanceof Document) {
        collection.insertOne((Document) data);
        System.out.println("Document inserted successfully.");
      } else {
        System.out.println("Data is not in the expected format.");
      }
    } finally {
      // Close the connection
      connection.close();
    }
  }

  public static void main(String[] args) {
    // Create a connection instance (replace with your actual Connection class)
    Connection connection = new Connection();

    // Create an instance of Create
    Create<Document> Create = new Create<>(connection);

    // Define the collection name
    String collectionName = "mycollection"; // Replace with your collection name

    // Create a new document to insert
    Document userDocument = new Document("username", "tun")
        .append("email", "john@example.com")
        .append("age", 30);

    // Insert the document
    Create.insertDocument(collectionName, userDocument);
  }
}
