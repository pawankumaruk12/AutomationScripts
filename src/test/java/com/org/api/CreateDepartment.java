package com.org.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Department;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateDepartment extends CommonLogin {

    @Test
    public void testDepartmentCreation200() {
        String projectId = (String) Repository.getValue("projectId");
        Department department = new Department();
        department.setName("AutoAccounts");
        department.setDepartmentTypeId("1");
        department.setDescription("Automation Accounts");
        department.setProjectId(projectId);
        String json = gson.toJson(department);
        Response createResponse  = given().
                body(json).
                when()
                .cookie(JSESSIONID, getJsessionId())
                .cookie(XSRF_TOKEN, getXSRFToken()).
                        contentType(ContentType.JSON).
                        post(API_PATH + "department/create").then()
                .assertThat().statusCode(200).and().extract().response();
        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(createResponse.getBody().asString()).getAsJsonObject();
        projectId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("department").get("projectId").getAsString();
        Repository.addData("projectId", projectId);
        String departmentId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("department").get("id").getAsString();
        Repository.addData("departmentId", departmentId);
        String name = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("department").get("name").getAsString();
        Repository.addData("name", name);
        String description = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("department").get("description").getAsString();
        Repository.addData("description", description);
        String departmentTypeId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("department").get("departmentTypeId").getAsString();
        Repository.addData("departmentTypeId", departmentTypeId);
        String versionId = fullBody.get(RESULTS).getAsJsonArray().get(fullBody.get(RESULTS).getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("department").get("versionId").getAsString();
        Repository.addData("versionId", versionId);
    }

 /*   @Test(dependsOnMethods = {"testDepartmentCreation415"})
    public void CreateDepartments403() throws Exception {
        String projectId = (String) Repository.getValue("projectId");
        Department department = new Department();
        department.setName("AutoAccounts");
        department.setDepartmentTypeId("1");
        department.setDescription("Automation Accounts");
        department.setProjectId(projectId);
        String json = gson.toJson(department);
        response = given().
                body(json).
                when()
                .contentType(ContentType.JSON).
                        post(API_PATH + "department/create").then()
                .assertThat().statusCode(403).and().extract().response();
    }*/

    @Test(dependsOnMethods = {"testDepartmentCreation200"})
    public void testDepartmentCreation415() {
        String projectId = (String) Repository.getValue("projectId");

        Department department = new Department();
        department.setName("AutoAccounts");
        department.setDepartmentTypeId("1");
        department.setDescription("Automation Accounts");
        department.setProjectId(projectId);
        String json = gson.toJson(department);
        response = given().
                body(json).
                when()
                .cookie(JSESSIONID, getJsessionId())
                .cookie(XSRF_TOKEN, getXSRFToken()).
                //contentType(ContentType.JSON).
                        post(API_PATH + "department/create").then()
                .assertThat().statusCode(415).and().extract().response();

    }
}
