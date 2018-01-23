package com.org.api;

import com.org.api.model.ProjectDocument;
import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteProjectDocument extends CommonLogin {

    @Test
    public void testDeleteProjectDocuments() throws Exception {

        String projectDocumentId = (String) Repository.getValue("projectDocumentId");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);
        ProjectDocument projectDocument = new ProjectDocument();
        projectDocument.setId(projectDocumentId);

       ;
        String json = gson.toJson(projectDocument);

        response = given().
                body(json).
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "projectdocument/delete/" + projectDocumentId).then()
                .assertThat().statusCode(200).and().extract().response();
    }

}