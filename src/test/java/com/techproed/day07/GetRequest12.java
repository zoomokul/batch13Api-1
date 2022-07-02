package com.techproed.day07;

import com.techproed.TestData.TestDataHerokuApp;
import com.techproed.testBase.TestBaseHerokuApp;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends TestBaseHerokuApp {
    /*
    https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
 dönen response body nin
  {
   "firstname": "Eric",
   "lastname": "Smith",
   "totalprice": 555,
   "depositpaid": false,
   "bookingdates": {
       "checkin": "2016-09-09",
       "checkout": "2017-09-21"
    }
} gibi olduğunu test edin
     */

    @Test
    public void test(){

        //url oluştur.

        spec02.pathParams("parametre1","booking",
                "parametre2",1);

        //expected

        TestDataHerokuApp testData=new TestDataHerokuApp();

        HashMap<String,Object> expectedDataMap=testData.setUpTestData();
        System.out.println(expectedDataMap);


        //request gönder


        Response response=given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        //De-serialization

        HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        System.out.println(actualDataMap);

        //assertion işlemi
        Assert.assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));

        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map) actualDataMap.get("bookingdates")).get("checkin"));
        Assert.assertEquals(   ((Map<?, ?>) expectedDataMap.get("bookingdates")).get("checkout"),
                ((Map)actualDataMap.get("bookingdates")).get("checkout"));







    }




}
