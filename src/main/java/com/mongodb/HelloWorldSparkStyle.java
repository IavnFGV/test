package com.mongodb;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by GFH on 18.03.2015.
 */
public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        Spark.get("/",new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "Hello World FROM Spark";
            }
        });
        Spark.get("/345",new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "Hello Wordsfgsdfg";
            }
        });
    }
}
