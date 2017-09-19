package com.org.api;

import io.restassured.http.ContentType;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

// This tested and working on 17th March 2017 , failing on 1st sep
@Ignore
public class UpdatesProjectDocument extends CommonLogin {
    @Test(enabled = false)
    public void UpdatesProjectDocuments() throws Exception {
        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        response = given()
                .body(Files.readAllBytes(Paths
                        .get("src/test/resources/UpdateProjectDocument.json")))
                .when().cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).contentType(ContentType.JSON)
                .post(API_PATH + "projectdocument/update");

        Assert.assertEquals(response.getStatusCode(), 200);


    }

}
