package com.org.api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;


public class PersonListNoAgent extends CommonLogin {
    @Test
    public void testPersonListNoAgents() throws Exception {
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        String PersonListNoAgent = "src/test/resources/PersonListNoAgent.json";
        response = given().body(Files.readAllBytes(Paths.get(PersonListNoAgent)))
                .when().cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).contentType(ContentType.JSON)
                .post(API_PATH + "person/list/noAgentPersonList").then().
                        assertThat().statusCode(200).and().extract().response();
    }
}
