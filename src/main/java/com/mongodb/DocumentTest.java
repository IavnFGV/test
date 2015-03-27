package com.mongodb;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;

import static com.mongodb.Helpers.printJson;
import static java.util.Arrays.asList;

/**
 * Created by GFH on 28.03.2015.
 */
public class DocumentTest {
    public static void main(String[] args) {
        Document document = new Document()
                            .append("str","MongoDB,Hello")
                            .append("int",42)
                .append("l",1L)
                .append("double",1.1)
                .append("b",false)
                .append("date",new Date())
                .append("objectId",new ObjectId())
                .append("null",null)
                .append("embenddedDoc",new Document("x",0))
                .append("list",asList(1,2,3));
        printJson(document);
//        String s = document.getString("str");
//        int i = document.getInteger("int");
    }
}
