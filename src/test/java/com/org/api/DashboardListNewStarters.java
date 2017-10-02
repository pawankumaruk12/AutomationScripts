package com.org.api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class DashboardListNewStarters extends CommonLogin {

    @Test
    public void testDashboardListNewStarters() throws Exception {

        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);

        response = given()
                .body(Files.readAllBytes(Paths
                        .get("src/test/resources/DashboardByMonth.json")))
                .when().cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).contentType(ContentType.JSON)
                .post(API_PATH + "dashboard/type").then().
                        assertThat().statusCode(200).and().extract().response();

    }

}
