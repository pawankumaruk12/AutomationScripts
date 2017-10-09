package com.org.api;

import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ImportProjectEnvelopeTemplate extends CommonLogin {
    @Test
    public void testImportProjectEnvelopeTemplate() throws Exception {
        String projectId = (String) Repository.getValue("projectId");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        response = given().
                body(Files.readAllBytes(Paths.get("src/test/resources/ImportProjectDocumentTemplate.json"))).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken)
                .contentType(ContentType.JSON)
                .post(API_PATH + "projectenvelopetemplate/import/" + projectId).then()
                .assertThat().statusCode(200).and().extract().response();
    }
}
