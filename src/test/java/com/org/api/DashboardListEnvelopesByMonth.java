package com.org.api;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class DashboardListEnvelopesByMonth extends CommonLogin {
    @Test
    public void testDashboardListEnvelopesByMonth() throws Exception {
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        response = given()
                .body(Files.readAllBytes(Paths
                        .get("src/test/resources/DashboardByMonth.json")))
                .when().cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).contentType(ContentType.JSON)
                .post(API_PATH + "dashboard/bymonth").then().
                        assertThat().statusCode(200).and().extract().response();
    }

    @Test(enabled = false)
    public void testDashboardListEnvelopesByMonthwithTeamMemberAccess() throws Exception {

        loginAsTeamMember();
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);

        response = given()
                .body(Files.readAllBytes(Paths
                        .get("src/test/resources/DashboardByMonth.json")))
                .when().cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).contentType(ContentType.JSON)
                .post(API_PATH + "dashboard/bymonth");

        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), 200);

    }
}
