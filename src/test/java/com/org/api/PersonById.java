package com.org.api;

import com.org.api.model.Repository;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import com.org.api.unittest.PersonService;


public class PersonById extends CommonLogin {


    @Test
    public void PersonByIds() throws Exception {
        String personId = (String) Repository.getValue("personId");

        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");


        response = given().
                when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).
                        contentType(ContentType.JSON)
                .post(API_PATH + "person/" + personId);

        AssertJUnit.assertEquals(response.getStatusCode(), 200);

    }

}
