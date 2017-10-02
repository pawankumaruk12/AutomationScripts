package com.org.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class DocumentType  extends CommonLogin {

    @Test
    public void testDocumentType() throws Exception {
        String jsessionId =  response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);

        response = given()
                .body(Files.readAllBytes(Paths
                        .get("src/test/resources/DocumentType.json")))
                .when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken)
                .contentType(ContentType.JSON)
                .post(API_PATH + "projectdocument/types").then()
                .assertThat().statusCode(200).and().extract().response();

        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();

        String documentTypeId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() -1).getAsJsonObject().get("id").getAsString();
        Repository.addData("documentTypeId",documentTypeId);

    }
}
