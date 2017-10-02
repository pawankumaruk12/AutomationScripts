package com.org.api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

// This forgot API is returning 200 even if we give wrong input
public class Logout extends CommonLogin {
    @Test
    public void Logouts() throws Exception {
        String jsessionId = response.cookie("JSESSION");
        String xsrfToken = response.cookie(XSRF_TOKEN);
        response = given()
                .body(Files.readAllBytes(Paths
                        .get("src/test/resources/logout.json"))).when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).contentType(ContentType.JSON)
                .post(API_PATH + "authentication/logout").then().
                        assertThat().statusCode(200).and().extract().response();

    }

}
