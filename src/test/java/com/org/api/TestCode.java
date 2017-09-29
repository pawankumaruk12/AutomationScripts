package com.org.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import java.io.*;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

public class TestCode {
    public static void main(String[] args) throws Exception {


       // testJsonObjectMethod();
      //  testSimpleSearch();
    // String base64value   org.apache.commons.io.IOUtils.toByteArray(new FileInputStream("src/test/resources/base64.txt"));
       // FileReader fr3 = new FileReader("src\\test\\resources\base64.txt");
       // String st = toString(fr3);

        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("base64");
        try {
           String base64 = org.apache.commons.io.IOUtils.toString(in, Charset.defaultCharset());
            System.out.println(base64);

        } finally {
            org.apache.commons.io.IOUtils.closeQuietly(in);
        }

    }



    private static void testJsonObjectMethod() {

        JsonObject obj = new JsonObject();

        obj.addProperty("Book Name", "Unnal Mudiyum Thambi");
        obj.addProperty("Book Name", "Magic of thinking Big");


        JsonArray jArray = new JsonArray();
        jArray.add("Language : English");
        jArray.add("School: Secondary");

        obj.add("school", jArray);
        obj.addProperty("Book Name", "Unnal Mudiyum Thambi");

        System.out.println(obj);
    }


    private static void testSimpleSearch() {


        String myString = "I am a String!";

        if (myString.indexOf("String") == -1) {
            System.out.println("String not Found!");

        } else {

            System.out.println("String found at: " + myString.indexOf("String"));

        }

    }

}

