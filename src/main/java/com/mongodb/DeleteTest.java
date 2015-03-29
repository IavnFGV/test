package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.Helpers.printJson;
import static com.mongodb.client.model.Filters.gt;

/**
 * Created by GFH on 29.03.2015.
 */
public class DeleteTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection = database.getCollection(DeleteTest.class.getSimpleName());

        collection.drop();


        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document().append("_id", i));
        }
//        collection.deleteMany( new Document("_id",new Document("$gt",4)));
        collection.deleteMany(gt("_id", 4));
        collection.deleteOne(gt("_id", 4));

        for (Document cur : collection.find().into(new ArrayList<Document>())) {
            printJson(cur);
        }
    }
}
