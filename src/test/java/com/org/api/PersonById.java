package com.org.api;

import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class PersonById extends CommonLogin {


    @Test
    public void PersonByIds() throws Exception {
        String personId = (String) Repository.getValue("personId");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        response = given().
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON)
                .post(API_PATH + "person/" + personId).then().
                        assertThat().statusCode(200).and().extract().response();

    }
}
