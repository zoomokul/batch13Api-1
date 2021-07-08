package com.techproed.day11;

import com.google.gson.JsonObject;
import com.techproed.TestData.TestDataJsonPlaceHolder;
import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequest03 extends TestBaseJsonPlaceHolder {
    /*
    https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
     }
     "userId": 55,
     "title": "Tidy your room",
     "completed": false
   }
Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
   {
     "userId": 55,
     "title": "Tidy your room",
     "completed": false,
     "id": …
    }
     */

    @Test
    public void test(){

        // url-endpoint oluşturuldu
        spec01.pathParam("parametre1","todos");


        //request body oluşturmalıyım.

        TestDataJsonPlaceHolder testData=new TestDataJsonPlaceHolder();
        JSONObject requestBody=testData.setUpPost03();
        System.out.println(requestBody);

        //request gönder

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin","password123").
                body(requestBody.toString()).
                when().post("/{parametre1}");

        response.prettyPrint();

        // De-Serialization -- Gson

        HashMap<String,Object> actualDataMap=response.as(HashMap.class);

        System.out.println(actualDataMap);
        Assert.assertEquals(testData.statusCode,response.getStatusCode());
        Assert.assertEquals(requestBody.getInt("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(requestBody.getString("title"),actualDataMap.get("title"));
        Assert.assertEquals(requestBody.getBoolean("completed"),actualDataMap.get("completed"));

        //JsonPath

        JsonPath json=response.jsonPath();
        Assert.assertEquals(requestBody.getInt("userId"),json.getInt("userId"));
        Assert.assertEquals(requestBody.getString("title"),json.getString("title"));
        Assert.assertEquals(requestBody.getBoolean("completed"),json.getBoolean("completed"));

        // Matchers class

        response.then().
                assertThat().statusCode(testData.statusCode).
                body("userId", equalTo(requestBody.getInt("userId")),
                        "title",equalTo(requestBody.getString("title")),
                        "completed",equalTo(requestBody.getBoolean("completed")));










    }





}
