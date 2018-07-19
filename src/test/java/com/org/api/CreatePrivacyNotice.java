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
            multiPart("notice", new File("src/test/resources/Template exported - 19-06-2018 11_06 AM (1).pdf")).
            when()
            .cookie(JSESSIONID, jsessionId)
            .cookie(XSRF_TOKEN, xsrfToken)
            .post(API_PATH+"project/" + projectID +"/privacyNotice").then().assertThat().statusCode(201).and().extract().response();
}

}

