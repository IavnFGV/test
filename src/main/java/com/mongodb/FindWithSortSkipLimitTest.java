package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.Helpers.printJson;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Sorts.descending;

/**
 * Created by GFH on 29.03.2015.
 */
public class FindWithSortSkipLimitTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection1 = database.getCollection(FindWithSortSkipLimitTest.class.getName());
        MongoCollection<Document> collection = database.getCollection(FindWithSortSkipLimitTest.class.getSimpleName());

        collection1.drop();
        collection.drop();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                collection.insertOne(new Document().append("i", i).append("j", j));
            }
        }

        Bson projection = fields(excludeId());
        //Bson sort = new Document("i",-1).append("j",-1);
        // Bson sort = orderBy(ascending("i"),descending("j"));
        // Bson sort = orderBy(descending("i"),descending("j"));
        Bson sort = descending("i", "j");

        List<Document> all = collection
                .find()
                .sort(sort)
                .limit(50)
                .skip(20)
                .projection(projection).into(new ArrayList<Document>());

        for (Document cur : all) {
            printJson(cur);
        }

    }
}
