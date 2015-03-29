package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.Helpers.printJson;
import static com.mongodb.client.model.Filters.*;

/**
 * Created by GFH on 29.03.2015.
 */
public class FindWithProjectionTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection1 = database.getCollection(FindWithProjectionTest.class.getSimpleName());
        MongoCollection<Document> collection = database.getCollection(FindWithProjectionTest.class.getName());
        collection.drop();
        collection1.drop();

        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document("x", new Random().nextInt(2)).append("y", new Random().nextInt(100)));
        }

        Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));

        // Bson projection  = new Document("x",0).append("_id",0);
        // Bson projection  = Projections.exclude("x","_id");
//        Bson projection  = Projections.fields(Projections.include("y"),
//                Projections.exclude("_id"));
        Bson projection = Projections.fields(Projections.include("y"),
                Projections.excludeId());

        List<Document> all = collection.find(filter)
                .projection(projection)
                .into(new ArrayList<Document>());

        for (Document cur : all) {
            printJson(cur);
        }
        //long count = collection.count(filter);
        //System.out.println();
        //System.out.println(count);


    }
}
