package com.org.api;

import com.google.gson.Gson;
import com.org.api.model.ProjectDocument;
import com.org.api.model.Repository;
import com.org.api.unittest.ProjectDocumentService;
import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class ProjectDocumentById extends CommonLogin {


    @Test
    public void ProjectDocumentByIds() throws Exception {

        String projectDocumentId = (String) Repository.getValue("projectDocumentId");
        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        ProjectDocument projectDocument = new ProjectDocument();
        projectDocument.setId(projectDocumentId);

        Gson gson = new Gson();
        String json = gson.toJson(projectDocument);


        response = given().
                body(json).
                when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "projectdocument/" + projectDocumentId).then()
                .assertThat().statusCode(200).and().extract().response();
    }

}
