package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import static java.util.Arrays.asList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(500).build();
        MongoClient client = new MongoClient(new ServerAddress(),options);
        //MongoClient client = new MongoClient();
        //MongoClient client = new MongoClient(asList(new ServerAddress("localhost", 27017)));
        //MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoDatabase db=client.getDatabase("test").withReadPreference(ReadPreference.secondary());

        //MongoCollection<Document> coll =  db.getCollection("test");
        MongoCollection<BsonDocument> coll =  db.getCollection("test",BsonDocument.class);

    }
}
