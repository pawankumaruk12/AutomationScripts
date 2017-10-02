package com.org.api;

import com.org.api.unittest.ProjectMemberService;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;


public class ProjectMemberById extends CommonLogin {

    @Test
    public void ListProjectMemberForCurrentUserByPaginations() throws Exception {

        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        String ProjectMemberId = ProjectMemberService.getLastProjectMemberId(
                null, jsessionId, xsrfToken);
        response = given()
                .body(Files.readAllBytes(Paths
                        .get("src/test/resources/ListbyProjectDoc.json")))
                .when().cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).contentType(ContentType.JSON)
                .post(API_PATH + "projectmember/" + ProjectMemberId).then().
                        assertThat().statusCode(200).and().extract().response();

    }
}
