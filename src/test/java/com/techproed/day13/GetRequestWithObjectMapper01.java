package com.techproed.day13;

import com.techproed.testBase.TestBaseJsonPlaceHolder;
import com.techproed.utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestWithObjectMapper01 extends TestBaseJsonPlaceHolder {

/*
https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
Dönen response ‘un status kodunun 200 ve body kısmının
{
   "userId": 10,
   "id": 198,
   "title": "quis eius est sint explicabo",
   "completed": true
}
Olduğunu Object Mapper kullanarak test edin

 */

    @Test
    public void test(){
        //url oluştur

        spec01.pathParams("parametre1","todos",
                "parametre2",198);


        //expected Data
        //----ilk olarak json ifademi bir Stringe atamalıyım

        String jsonData="{\n" +
                "   \"userId\": 10,\n" +
                "   \"id\": 198,\n" +
                "   \"title\": \"quis eius est sint explicabo\",\n" +
                "   \"completed\": true\n" +
                "}";

        //----çevirmek istediğim type'i belirliyorum
        //Object Mapper yöntemi De-Serialization yapar.

        Map<String,Object> expectedDataMap= JsonUtil.convertJsonToJava(jsonData,Map.class);
        System.out.println(expectedDataMap);

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        Map<String,Object> actualDataMap=JsonUtil.convertJsonToJava(response.asString(),Map.class);
        System.out.println(actualDataMap);

        //convertJsonToJava methodu string bir parametre kabul eder. response String formatta değildir
        // response String formatta göndermek için response.asString() methodunu kullandık

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataMap.get("id"),actualDataMap.get("id"));
        Assert.assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));









    }





}
