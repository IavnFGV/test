package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.mongodb.Helpers.printJson;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.*;
import static java.lang.Math.round;

/**
 * Created by GFH on 29.03.2015.
 */
public class Homework23 {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("students");
        MongoCollection<Document> collection = database.getCollection("grades");

        Bson sort = orderBy(ascending("student_id"), descending("score"));
        List<Document> all = collection.find(eq("type", "homework")).sort(sort).into(new ArrayList());

        List<Document> forDelete = new LinkedList<Document>();

        int i = 0;
        long privId = 0;
        long curId = 0;
        while (i < all.size()) {
            curId = round(all.get(i).getDouble("student_id"));

            if (privId != curId) {
                forDelete.add(all.get(i - 1));
                privId = curId;
            }
            i++;
            //TODO IF last docmet yas duplicate- it would not added to the forDelete list
        }
        for (Document cur : forDelete) {
            collection.deleteOne(cur);
        }

        for (int j = 0; j < all.size(); j++) {
            printJson(all.get(j));
        }
        for (int j = 0; j < forDelete.size(); j++) {
            printJson(forDelete.get(j));
        }
    }
}
