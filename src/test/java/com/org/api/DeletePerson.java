package com.org.api;

import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeletePerson extends CommonLogin {


    @Test
    public void testDeletePersons() throws Exception {
        String personId = (String) Repository.getValue("personId");

        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        response = given().
                when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "person/delete/" + personId).
                        then().
                        assertThat().statusCode(200).and().extract().response();


    }

}


