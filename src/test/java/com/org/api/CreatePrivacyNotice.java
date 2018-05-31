package com.org.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.PrivacyNotice;
import com.org.api.model.Project;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class CreatePrivacyNotice extends CommonLogin{

@Test
    public void testcreatePrivacyNotice() throws Exception{

    String projectID = (String) Repository.getValue("projectId");
    String jsessionId = response.cookie(JSESSIONID);
    String xsrfToken = response.cookie(XSRF_TOKEN);
    response = given().contentType("multipart/form-data").
            multiPart("notice", new File("src/test/resources/Privacy Notice 1.pdf")).
            when()
            .cookie(JSESSIONID, jsessionId)
            .cookie(XSRF_TOKEN, xsrfToken)
            .post(API_PATH+"project/" + projectID +"/privacyNotice").then().assertThat().statusCode(201).and().extract().response();
}


/*
@Test
public void testAgreementNotRequired() throws Exception{
    String projectId = (String) Repository.getValue("projectId");
    String jsessionId = response.cookie(JSESSIONID);
    String xsrfToken = response.cookie(XSRF_TOKEN);

    String json= gson.toJson(projectId);
    response = given().body(json).contentType(ContentType.JSON).when().cookie(JSESSIONID, jsessionId)
            .cookie(XSRF_TOKEN, xsrfToken)
            .post(API_PATH+"project/" + projectId +"/privacyNotice/agreementnotrequired").then().assertThat().statusCode(200).and().extract().response();
} */
}

