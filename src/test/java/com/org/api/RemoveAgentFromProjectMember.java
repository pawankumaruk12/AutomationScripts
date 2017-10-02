package com.org.api;

import io.restassured.http.ContentType;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

@Ignore
public class RemoveAgentFromProjectMember extends CommonLogin {

    @Test(enabled = false)
    public void RemoveAgentFromProjectMembers() throws Exception {
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        response = given().
                body(Files.readAllBytes(Paths.get("src/test/resources/ListbyProjectDoc.json"))).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "projectmember/removeagent/:id").then().
                        assertThat().statusCode(200).and().extract().response();
    }
}
