package com.techproed.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class TestBasePetStore {
    protected RequestSpecification spec01;

    @Before
    public void setup(){
        spec01=new RequestSpecBuilder().
                setBaseUri("https://petstore.swagger.io/v2").
                build();

    }
}
