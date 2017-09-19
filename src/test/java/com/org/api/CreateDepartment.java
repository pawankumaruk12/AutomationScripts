package com.org.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.org.api.model.Department;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class CreateDepartment extends CommonLogin {


    @Test
    public void testDepartmentCreation() {
        String projectId = (String) Repository.getValue("projectId");

        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        Department department = new Department();
        department.setName("AutoAccounts");
        department.setDepartmentTypeId("1");
        department.setDescription("Automation Accounts");
        department.setProjectId(projectId);

        Gson gson = new Gson();
        String json = gson.toJson(department);

        response = given().
                body(json).
                when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "department/create");

        JsonParser parser = new JsonParser();
        JsonObject fullBody = parser.parse(response.getBody().asString()).getAsJsonObject();

        //String
        projectId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("department").get("projectId").getAsString();
        Repository.addData("projectId", projectId);


        String departmentId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("department").get("id").getAsString();
        Repository.addData("departmentId", departmentId);

        String name = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("department").get("name").getAsString();
        Repository.addData("name", name);

        String description = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("department").get("description").getAsString();
        Repository.addData("description", description);

        String departmentTypeId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("department").get("departmentTypeId").getAsString();
        Repository.addData("departmentTypeId", departmentTypeId);

        String versionId = fullBody.get("results").getAsJsonArray().get(fullBody.get("results").getAsJsonArray().size() - 1).getAsJsonObject().getAsJsonObject("department").get("versionId").getAsString();
        Repository.addData("versionId", versionId);

    }


    @Test(enabled = false)
    public void CreateDepartments() throws Exception {
        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");
        String CreateDeptJson = "src/test/resources/CreateDept.json";

        response = given().
                body(Files.readAllBytes(Paths.get(CreateDeptJson))).
                when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "department/create");
        System.out.println(response.getBody().asString());
        AssertJUnit.assertEquals(response.getStatusCode(), 200);
        if (response.getStatusCode() == 200) {
            System.out.println("API is working fine");
            System.out.println(response.getStatusCode());
        } else {
            System.out.println("API is not working");
        }

    }

}
