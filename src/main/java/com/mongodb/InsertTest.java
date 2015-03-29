package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static java.util.Arrays.asList;

/**
 * Created by GFH on 29.03.2015.
 */
public class InsertTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("insertTest");
        collection.drop();

        Document smith = new Document("name", "Smith")
                .append("age", 30)
                .append("professional", "programmer");
        Document jones = new Document("name", "Jones")
                .append("age", 25)
                .append("professional", "hacker");


        collection.insertMany(asList(smith, jones));
    }
}
