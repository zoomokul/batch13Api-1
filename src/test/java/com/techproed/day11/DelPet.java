package com.techproed.day11;

import com.techproed.TestData.TestDataDummy;
import com.techproed.TestData.TestDataPetStore;
import com.techproed.testBase.TestBasePetStore;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DelPet extends TestBasePetStore {
    @Test
    public void test(){

        //url oluştur

        spec01.pathParams("parametre1","pet",
                "parametre2",5);

        //expected Data oluştur
        TestDataPetStore testData=new TestDataPetStore();
        JSONObject expectedData=testData.setUpDelete01();


        //request gönder

        Response response=given().
                spec(spec01).

                when().
                delete("/{parametre1}/{parametre2}");

        response.prettyPrint();
/*
        response.then().
                assertThat().
                statusCode(expectedData.getInt("statusCode")).
                body("type", equalTo(expectedData.getString("type")),
                        "message",equalTo(expectedData.getString("message")),
                        "code",equalTo(expectedData.getInt("code")));
*/








    }

}
