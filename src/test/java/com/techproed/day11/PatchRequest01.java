package com.techproed.day11;

import com.techproed.TestData.TestDataJsonPlaceHolder;
import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PatchRequest01 extends TestBaseJsonPlaceHolder {

    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
   {
      "title": "API calismaliyim"

     }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "userId": 10,
 "title": "API calismaliyim"
 "completed": true,
 "id": 198
}
     */
    @Test
    public  void test(){

        //url
        spec01.pathParams("parametre1","todos",
                "parametre2",198);


        //requestBody  -request yaparken kullanacağız

        TestDataJsonPlaceHolder testData=new TestDataJsonPlaceHolder();
        JSONObject requestBody=testData.SetUpPatch01();
        System.out.println(requestBody);

        //expected-- request body den farklı olduğu için oluşturduk. Assertion işlemini yaparken kullanacağız

        JSONObject expectedData=testData.SetUpexpectedBody();
        System.out.println(expectedData);

        // request oluştur

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin","password123").
                body(requestBody.toString()).
                when().
                patch("/{parametre1}/{parametre2}");

        response.prettyPrint();

        //De-Seriallization

        HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        System.out.println(actualDataMap);

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedData.getBoolean("completed"),actualDataMap.get("completed"));
        Assert.assertEquals(expectedData.getInt("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedData.getString("title"),actualDataMap.get("title"));


    }








}
