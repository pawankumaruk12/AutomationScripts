package com.org.api;

import com.org.api.model.Repository;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UpdatePrivacyNotice extends CommonLogin{
    @Test
    public void testupdatePrivacyNotice() throws Exception {

        String projectId = (String) Repository.getValue("projectId");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        response = given().contentType("multipart/form-data").
                multiPart("notice", new File("src/test/resources/CrewStart-Global Privacy Notice.pdf")).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken)
                .put(API_PATH+"project/" + projectId +"/privacyNotice").then().assertThat().statusCode(200).and().extract().response();

    }

    }
