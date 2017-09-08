package com.org.api;

import com.google.gson.Gson;
import com.org.api.model.Project;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateProject extends CommonLogin {

    @Test
    public void testUpdateProject() throws Exception {

        String companyId = (String) Repository.getValue("companyId");
        String id = (String) Repository.getValue("projectId");
        String name = (String) Repository.getValue("name");
        String productionId = (String) Repository.getValue("productionId");
        //String typeId = (String) Repository.getValue("typeId");
        String description = (String) Repository.getValue("description");
        String versionId = (String) Repository.getValue("versionId");

        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        Project project = new Project();

        project.setId(id);
        project.setCompanyId(companyId);
        project.setDescription(description);
        project.setProductionId(null);
        project.setName(name);
        project.setTypeId(1);
        project.setVersionId(versionId);

        Gson gson = new Gson();
        String json = gson.toJson(project);

        response = given().
                body(json).
                when()
                .cookie("JSESSIONID",jsessionId)
                .cookie("XSRF-TOKEN",xsrfToken).
                contentType(ContentType.JSON).
                post(API_PATH + "project/update").
                then().
                assertThat().statusCode(200).and().extract().response();


    }
}
