package com.org.api;

import com.org.api.unittest.ProjectDocumentService;
import io.restassured.http.ContentType;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteProjectDocument extends CommonLogin {
    public static String PROJECTDOCUMENT_ID;

    @Test(enabled = false)
    public void DeleteProjectDocuments() throws Exception {
        String jsessionId = response.cookie("JSESSIONID");
        String xsrfToken = response.cookie("XSRF-TOKEN");

        PROJECTDOCUMENT_ID = ProjectDocumentService.getLastDocumentId(null, jsessionId, xsrfToken);
        System.out.println(PROJECTDOCUMENT_ID);
        response = given().
                when()
                .cookie("JSESSIONID", jsessionId)
                .cookie("XSRF-TOKEN", xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "projectdocument/delete/" + PROJECTDOCUMENT_ID);

        AssertJUnit.assertEquals(response.getStatusCode(), 200);

    }

}