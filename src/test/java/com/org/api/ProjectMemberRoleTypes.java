package com.org.api;

import com.google.gson.Gson;
import com.org.api.model.Department;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProjectMemberRoleTypes extends CommonLogin {

    @Test
    public void testProjectMemberRoleType() throws Exception {
        String departmentId = (String) Repository.getValue("departmentId");
        String projectId = (String) Repository.getValue("projectId");

        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        Department department = new Department();
        department.setId(departmentId);

        Gson gson = new Gson();
        String json = gson.toJson(department);


        response = given().

                body(json).
                when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "projectmember/roletypes/" + departmentId);


        AssertJUnit.assertEquals(response.getStatusCode(), 200);


    }
}
