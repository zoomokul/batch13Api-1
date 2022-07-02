package com.techproed.TestData;

import org.json.JSONObject;

public class TestDataPetStore {
    public JSONObject setUpDelete01(){
        /*
 {
  "code": 200,
  "type": "unknown",
  "message": "5"
}
         */

        JSONObject expectedData=new JSONObject();
        expectedData.put("code",200);
        expectedData.put("type","unknown");
        expectedData.put("message","5");
        expectedData.put("statusCode",200);
        return expectedData;

    }
}
