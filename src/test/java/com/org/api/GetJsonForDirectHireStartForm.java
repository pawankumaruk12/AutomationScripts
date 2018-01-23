package com.org.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetJsonForDirectHireStartForm extends CommonLogin {
    private static final String GET_API_PATH = ROOT_PATH + "/modular-forms/";

    @Test
    public void testProjectById() throws Exception {
        String documentId = (String) Repository.getValue("documentId");
        String documentName = (String) Repository.getValue("documentName");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        response = given().
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken)
                .contentType(ContentType.JSON)
                .get(GET_API_PATH + "SD_Crew_Direct_Hire_Start_Form.json").then()
                .assertThat().statusCode(200).and().extract().response();
        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();
        fullBody.add("formname", new JsonPrimitive("SD_Crew_Direct_Hire_Start_Form.json"));
        JsonObject model = fullBody.getAsJsonObject("model");
        JsonArray documents = model.getAsJsonArray("documents");
        JsonObject newDocument = new JsonObject();
        newDocument.addProperty("name", documentName);
        newDocument.addProperty("documentid", documentId);
        newDocument.addProperty("numberOfPages", 1);
        newDocument.addProperty("ticked", false);
        newDocument.addProperty("tickedByAgent", false);
        newDocument.addProperty("required", true);
        newDocument.add("mappings", new JsonArray());
        documents.add(newDocument);
        model.add("documents", documents);
        fullBody.add("model", model);
        String directHireJson = fullBody.toString();
        Repository.addData("json", directHireJson);
    }
}
