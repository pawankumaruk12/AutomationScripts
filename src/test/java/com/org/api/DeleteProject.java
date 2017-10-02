package com.org.api;

import com.org.api.model.Repository;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class DeleteProject extends CommonLogin {

    @Test
    public void testDeleteProjects() throws Exception {
        String projectId = (String) Repository.getValue("projectId");
        String jsessionId = response.cookie(JSESSIONID);
        String xsrfToken = response.cookie(XSRF_TOKEN);

        response = given().
                when()
                .cookie(JSESSIONID, jsessionId)
                .cookie(XSRF_TOKEN, xsrfToken).
                        contentType(ContentType.JSON).
                        post(API_PATH + "project/delete/" + projectId).
                        then().
                        assertThat().statusCode(200).and().extract().response();
    }
}


