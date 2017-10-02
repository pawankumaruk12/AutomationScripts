package com.org.api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

// No need to test this api, as this api is not in use
public class DeleteProjectMember extends CommonLogin {
    @Test(enabled = false)
    public void DeleteProjectMembers() throws Exception {
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        response = given().
                body(Files.readAllBytes(Paths.get("src/test/resources/CompanyType.json"))).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "ProjectMember/delete/").then().
                        assertThat().statusCode(200).and().extract().response();
    }
}
