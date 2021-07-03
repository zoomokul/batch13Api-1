package com.techproed.day07;

import com.techproed.TestData.TestDataJsonPlaceHolder;
import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest11TestData extends TestBaseJsonPlaceHolder {


/*
https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
 Dönen response un
 Status kodunun 200, dönen body de,
 "completed": değerinin false
"title”: değerinin “quis ut nam facilis et officia qui”
"userId" sinin 1 ve header değerlerinden
 "Via" değerinin “1.1 vegur” ve
 "Server" değerinin “cloudflare” olduğunu test edin…
 */

    @Test
    public void test(){
        //1-url oluştur
        spec01.pathParams("parametre1","todos",
                "parametre2",2);

        //2- expected data oluştur

        TestDataJsonPlaceHolder expectedObje=new TestDataJsonPlaceHolder();
        //TestDatasındaki expected map e ulaşabilmek için  TestDataJsonPlaceHolder
        // class ından bir obje oluşturdum.
        HashMap<String,Object> expectedDataMap=expectedObje.setUpTestData();
        //oluşturduğum obje üzerinden expectedDataMape ulaştım ve bu mapi classımda oluşturduğum
        // map e aktardım.

        System.out.println("ExpectedData");
        System.out.println(expectedDataMap);
        System.out.println("--------------------------------------");



        //3-Request gönder
        Response response=given().
                accept("application/json").
                spec(spec01).when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        //4-Actual Datayı oluştur

        HashMap<String ,Object> actualDataMap=response.as(HashMap.class); // api dan gelen response body i HashMap gibi yapıp

        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
        Assert.assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataMap.get("Via"),response.getHeader("Via"));
        Assert.assertEquals(expectedDataMap.get("Server"),response.getHeader("Server"));
        Assert.assertEquals("application/json; charset=utf-8",response.contentType());

        System.out.println("Actual Data");
        System.out.println("---------------------");
        System.out.println(actualDataMap);



    }





}
