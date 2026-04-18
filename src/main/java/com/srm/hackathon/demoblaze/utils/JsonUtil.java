package com.srm.hackathon.demoblaze.utils;

import java.io.FileReader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtil {

    private static final String FILE_PATH = "src/test/resources/testdata/orderData.json";

    // 🔥 Get JSON object
    public static JsonObject getTestData(String key) {
        try {
            JsonObject jsonObject = JsonParser
                    .parseReader(new FileReader(FILE_PATH))
                    .getAsJsonObject();

            return jsonObject.getAsJsonObject(key);

        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON: " + e.getMessage());
        }
    }

    // 🔥 Get value
    public static String getValue(String key, String field) {
        return getTestData(key).get(field).getAsString();
    }
}