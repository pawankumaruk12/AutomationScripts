package com.org.api;

import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ListAgenciesByPagination extends CommonLogin {
    @Test
    public void ListAgenciesByPaginations() throws Exception {
        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");
        String ListAgencies = "src/test/resources/ListAgenciesByPagination.json";
        response = given().body(Files.readAllBytes(Paths.get(ListAgencies))).when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
                .post(API_PATH + "agency/list");
        AssertJUnit.assertEquals(response.getStatusCode(), 200);

    }

}
