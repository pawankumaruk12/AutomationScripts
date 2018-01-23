package com.org.api;

import com.org.api.model.Department;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProjectMemberRoleTypes extends CommonLogin {

    @Test
    public void testProjectMemberRoleType() throws Exception {
        String departmentId = (String) Repository.getValue("departmentId");
        String projectId = (String) Repository.getValue("projectId");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        Department department = new Department();
        department.setId(departmentId);

        String json = gson.toJson(department);
        response = given().
                body(json).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "projectmember/roletypes/" + departmentId).then().
                        assertThat().statusCode(200).and().extract().response();
    }
}
