package com.techproed.day11;

import com.techproed.testBase.TestBaseJsonPlaceHolder;
import org.junit.Test;

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

        //expected - request data






    }




}
