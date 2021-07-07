package com.techproed.day10;

import com.techproed.TestData.TestDataDummy;
import com.techproed.testBase.TestBaseDummy;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends TestBaseDummy {

    /*
    http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
    "name":"Ahmet Aksoy",
           "salary":"1000",
           "age":"18",
           "profile_image": ""
}
gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
{
   "status": "success",
           "data": {
        “id”:…
   },
   "message": "Successfully! Record has been added."
}
olduğunu test edin
     */

    @Test
    public void test(){

       //url oluştur

      spec03.pathParam("parametre1","create");

       // expected data dan önce post yaparken request body gönderdiğim için onu oluşturmalıyım.
        TestDataDummy testData=new TestDataDummy();
        HashMap<String,Object>requestBodyMap=testData.SetUpTestData3();
        System.out.println(requestBodyMap);

        //expected datayı oluşturalım
        HashMap<String,Object> expectedDataMap=testData.SetUpTestData4();
        System.out.println(expectedDataMap);


        //request oluştur

        //autohorazion--> Belli bir database yada servera ulaşabilme yetkisi
        // ya da orada yapabildiğim işlemleri belirler.

        Response response=given().
                accept("application/json").
                spec(spec03).
                auth().basic("admin","password123").
                body(requestBodyMap).
                when().post("/{parametre1}");

        response.prettyPrint();

        HashMap<String,Object> actualDataMap=response.as(HashMap.class);

        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());

        Assert.assertEquals(expectedDataMap.get("status"),actualDataMap.get("status"));
        Assert.assertEquals(expectedDataMap.get("message"),actualDataMap.get("message"));


    }





}
