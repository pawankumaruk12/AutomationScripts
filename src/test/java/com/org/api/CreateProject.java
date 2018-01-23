package com.org.api;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Project;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import static io.restassured.RestAssured.given;


public class CreateProject extends CommonLogin {


    @Test
    public void testProjectCreation() {
        String companyId = (String) Repository.getValue("companyId");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        Project project = new Project();
        project.setName("AutoProject" + new Date());
        project.setDescription("Automation Project");
        project.setProductionId(null);
        project.setTypeId(2);
        project.setCompanyId(companyId);

       ;
        String json = gson.toJson(project);

        response = given().
                body(json).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "project/create");

        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();

        String projectId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("project").get("id").getAsString();
        Repository.addData("projectId", projectId);
        String name = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("project").get("name").getAsString();
        Repository.addData("name", name);
        String typeId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("project").get("typeId").getAsString();
        Repository.addData("typeId", typeId);
        String description = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("project").get("description").getAsString();
        Repository.addData("description", description);
        String versionId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("project").get("versionId").getAsString();
        Repository.addData("versionId", versionId);

    }


    @Test(enabled = false)
    public void testCreateProjectsNegativeCase() throws Exception {
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        String CreateProjectJson = "src/test/resources/CreateProject.json";

        response = given().
                body(Files.readAllBytes(Paths.get(CreateProjectJson))).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "project/create").then()
                .assertThat().statusCode(201).and().extract().response();

    }
}
