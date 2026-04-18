package com.srm.hackathon.demoblaze.dataprovider;


import org.testng.annotations.DataProvider;

import com.google.gson.JsonObject;
import com.srm.hackathon.demoblaze.utils.JsonUtil;

public class DataProviders {

    @DataProvider(name = "orderData")
    public static Object[][] getOrderData() {

        JsonObject data = JsonUtil.getTestData("validOrder");

        return new Object[][] {
            {
                data.get("name").getAsString(),
                data.get("country").getAsString(),
                data.get("city").getAsString(),
                data.get("card").getAsString(),
                data.get("month").getAsString(),
                data.get("year").getAsString()
            }
        };
    }
}