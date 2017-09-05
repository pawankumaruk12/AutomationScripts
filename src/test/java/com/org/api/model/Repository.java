package com.org.api.model;

import java.util.HashMap;
import java.util.Map;

public class Repository {

    private static Map<String, Object> storage = new HashMap<>();

    public static void addData(String field, Object value){
        storage.put(field, value);

    }

    public static Object getValue(String field){
       return storage.get(field);
    }





}
