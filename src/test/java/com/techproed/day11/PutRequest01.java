package com.techproed.day11;

import com.techproed.TestData.TestDataJsonPlaceHolder;
import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PutRequest01 extends TestBaseJsonPlaceHolder {
    /*

    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde
   {
      "userId": 21,
      "title": "Wash the dishes",
      "completed": false
     }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "userId": 21,
 "title": "Wash the dishes",
 "completed": false,
 "id": 198
}
     */


    @Test
    public void test(){
        //url oluştur
        spec01.pathParams("parametre1","todos",
                                "parametre2",198  )  ;

        //request data

        TestDataJsonPlaceHolder testData=new TestDataJsonPlaceHolder();
        JSONObject requestBody=testData.SetUpPut01();
        System.out.println(requestBody);

        //expected
        //benim request body ile ,request gönderdiğimdeki response dan dönecek olan data aynı olduğu için
        //assertion yaparken kullanacağım expected datayı oluşturmama gerek yok. assertion işleminde
        //requestbody i kullanabilirim.


        //request gönder

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin","password123").
                body(requestBody.toString()).when().put("/{parametre1}/{parametre2}");

        response.prettyPrint();

        //JsonPath ile

        JsonPath json=response.jsonPath();
       // json.prettyPrint();

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(requestBody.getInt("userId"),json.getInt("userId"));
        Assert.assertEquals(requestBody.getString("title"),json.getString("title"));
        Assert.assertEquals(requestBody.getBoolean("completed"),json.getBoolean("completed"));









    }




}
