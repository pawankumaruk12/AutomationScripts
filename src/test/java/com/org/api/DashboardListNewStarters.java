package com.org.api;

import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class DashboardListNewStarters extends CommonLogin {

    @Test
    public void testDashboardListNewStarters() throws Exception {

        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        response = given()
                .body(Files.readAllBytes(Paths
                        .get("src/test/resources/DashboardByMonth.json")))
                .when().cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
                .post(API_PATH + "dashboard/type");

        AssertJUnit.assertEquals(response.getStatusCode(), 200);

    }


}
