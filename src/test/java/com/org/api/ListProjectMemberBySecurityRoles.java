package com.org.api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ListProjectMemberBySecurityRoles extends CommonLogin {
    @Test(enabled = false)
    public void ListProjectMemberSecurityRoles() throws Exception {

        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        response = given()
                .body(Files.readAllBytes(Paths
                        .get("src/test/resources/ListbySecurityRoles.json")))
                .when().cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON)
                .post(API_PATH + "projectMember/list/bysecurityroles").then().
                        assertThat().statusCode(200).and().extract().response();

    }
}
