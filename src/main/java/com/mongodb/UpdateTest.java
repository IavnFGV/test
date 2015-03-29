package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.Helpers.printJson;
import static com.mongodb.client.model.Filters.gte;

/**
 * Created by GFH on 29.03.2015.
 */
public class UpdateTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection1 = database.getCollection(UpdateTest.class.getName());
        MongoCollection<Document> collection = database.getCollection(UpdateTest.class.getSimpleName());

        collection1.drop();
        collection.drop();
        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document().append("_id", i).append("x", i));
        }
//        collection.replaceOne(eq("x",5),new Document("_id",5).append("x",30).append("update",true));
//        collection.updateOne(eq("x",5),new Document("$set",new Document("x",20)));
//        collection.updateOne(eq("_id",9),new Document("$set",new Document("x",20)),
//                new UpdateOptions().upsert(false));
        collection.updateMany(gte("_id", 5), new Document("$inc", new Document("x", 2)));

        List<Document> all = collection.find()
                .into(new ArrayList<Document>());

        for (Document cur : all) {
            printJson(cur);
        }
    }
}
