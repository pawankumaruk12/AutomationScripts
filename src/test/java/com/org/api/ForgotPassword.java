package com.org.api;
//Tested and working on 29th Aug 2017

import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ForgotPassword extends CommonLogin {
    @Test
    public void ForgotPasswords() throws Exception {
        response = given()
                .body(Files.readAllBytes(Paths
                        .get("src/test/resources/ForgotPassword.json"))).
                        when()
                .contentType(ContentType.JSON)
                .post(API_PATH + "authentication/forgotpassword");

        AssertJUnit.assertEquals(response.getStatusCode(), 200);

    }

}
