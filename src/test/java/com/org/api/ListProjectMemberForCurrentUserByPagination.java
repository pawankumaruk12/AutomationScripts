package com.org.api;

import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;


public class ListProjectMemberForCurrentUserByPagination extends CommonLogin {
    @Test(enabled = false)
    public void ListProjectMemberForCurrentUserByPaginations() throws Exception {

        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        response = given()
                .body(Files.readAllBytes(Paths
                        .get("src/test/resources/ListbyProjectDoc.json")))
                .when().cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
                .post(API_PATH + "ProjectMember/listcurrentuser");
        System.out.println(response.getBody().asString());
        AssertJUnit.assertEquals(response.statusCode(), 200);

        if (response.getStatusCode() == 200) {
            System.out.println("API is working fine");
            System.out.println(response.getStatusCode());
        } else {
            System.out.println("API is not working fine");
        }

    }

}
